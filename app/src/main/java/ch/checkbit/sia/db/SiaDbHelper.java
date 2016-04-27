package ch.checkbit.sia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.checkbit.sia.helpers.CharismaQuests;
import ch.checkbit.sia.helpers.Quest;
import ch.checkbit.sia.helpers.Todo;

/**
 * SIA
 *
 *
 * Created by zeta on 08/01/16.
 */
public class SiaDbHelper extends SQLiteOpenHelper {

    // increment the database version when schema changes.
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Sia.db";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-DD HH:mm:ss.SSS";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_CHARISMA_QUESTS_TABLE =
            "CREATE TABLE " + DbContract.CharismaQuest.TABLE_NAME + " (" +
                    DbContract.CharismaQuest._ID + " INTEGER PRIMARY KEY," +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_ID + INTEGER_TYPE + COMMA_SEP +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_TITLE + TEXT_TYPE + COMMA_SEP +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_ICON + TEXT_TYPE + COMMA_SEP +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_DESC + TEXT_TYPE + COMMA_SEP +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_INFO + TEXT_TYPE + COMMA_SEP +
                    DbContract.CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + TEXT_TYPE +
            " )";

    private static final String SQL_CREATE_TODO_TABLE =
            "CREATE TABLE " + DbContract.Todos.TABLE_NAME + " (" +
                    DbContract.Todos._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_CREATE_DATE + TEXT_TYPE + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_DESC + TEXT_TYPE + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_DONE + INTEGER_TYPE + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_DONE_DATE + TEXT_TYPE + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_TYPE + TEXT_TYPE + COMMA_SEP +
                    DbContract.Todos.COLUMN_NAME_TODO_NOTE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_CHARISMA_QUESTS_TABLE =
            "DROP TABLE IF EXISTS " + DbContract.CharismaQuest.TABLE_NAME;

    private static final String SQL_DELETE_TODOS_TABLE =
            "DROP TABLE IF EXISTS " + DbContract.Todos.TABLE_NAME;


    private static final String[] CHARISMA_QUESTS_PROJECTION = {
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_ID,
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_TITLE,
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_ICON,
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_DESC,
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_INFO,
            DbContract.CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP
    };

    private static final String[] TODO_PROJECTION = {
            DbContract.Todos._ID,
            DbContract.Todos.COLUMN_NAME_TODO_DESC,
            DbContract.Todos.COLUMN_NAME_TODO_TYPE,
            DbContract.Todos.COLUMN_NAME_TODO_NOTE,
            DbContract.Todos.COLUMN_NAME_TODO_CREATE_DATE,
            DbContract.Todos.COLUMN_NAME_TODO_DONE,
            DbContract.Todos.COLUMN_NAME_TODO_DONE_DATE
    };


    public SiaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHARISMA_QUESTS_TABLE);
        db.execSQL(SQL_CREATE_TODO_TABLE);
        initializeQuestsData(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CHARISMA_QUESTS_TABLE);
        db.execSQL(SQL_DELETE_TODOS_TABLE); // TODO remove this later
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void initializeQuestsData(SQLiteDatabase db) {
        for (Quest q: CharismaQuests.getQuests()) {
            insertCharismaQuest(db, q.questId, q.icon, q.title, q.description, q.info);
        }
    }

    private long insertCharismaQuest(SQLiteDatabase db, long questId, String icon, String title, String desc, String info){
        ContentValues values = new ContentValues();
        values.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_ID, questId);
        values.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_ICON, icon);
        values.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_TITLE, title);
        values.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_DESC, desc);
        values.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_INFO, info);

        return db.insert(
                DbContract.CharismaQuest.TABLE_NAME,
                null,
                values);
    }

    public long insertTodo(SQLiteDatabase db, String desc, String note, Todo.Type type){
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        ContentValues values = new ContentValues();
        values.put(DbContract.Todos.COLUMN_NAME_TODO_CREATE_DATE, sdf.format(new Date()));
        values.put(DbContract.Todos.COLUMN_NAME_TODO_DESC, desc);
        values.put(DbContract.Todos.COLUMN_NAME_TODO_NOTE, note);
        values.put(DbContract.Todos.COLUMN_NAME_TODO_TYPE, type.name());

        return db.insert(
                DbContract.Todos.TABLE_NAME,
                null,
                values);
    }


    public List<Quest> getQuests(SQLiteDatabase db) {

        List<Quest> quests = new ArrayList<>();
        String sortOrder =
                DbContract.CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + " DESC";

        Cursor c = db.query(
                DbContract.CharismaQuest.TABLE_NAME,  // The table to query
                CHARISMA_QUESTS_PROJECTION,           // The columns to return
                null,                                 // The columns for the WHERE clause
                null,                                 // The values for the WHERE clause
                null,                                 // don't group the rows
                null,                                 // don't filter by row groups
                sortOrder                             // The sort order
        );

        if (c != null) {
            c.moveToFirst();

            int count = c.getCount();

            for (int i = 0; i < count; i++) {
                Quest quest = fromCursor(c);
                quests.add(quest);
                c.moveToNext();
            }
            c.close();
        }

        return quests;
    }


    public Quest getQuestByTimestamp(SQLiteDatabase db, String lastActiveTimestamp) {

        Quest quest = null;

        Cursor c = db.query(
                DbContract.CharismaQuest.TABLE_NAME,                                        // The table to query
                CHARISMA_QUESTS_PROJECTION,                                                 // The columns to return
                DbContract.CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + "=?",    // The columns for the WHERE clause
                new String[]{lastActiveTimestamp},                                          // The values for the WHERE clause
                null,                                                                       // don't group the rows
                null,                                                                       // don't filter by row groups
                null                                                                        // The sort order
        );

        if ( c != null ) {

            if (c.getCount() > 0) {
                c.moveToFirst();
                quest = fromCursor(c);
            }
            c.close();
        }
        return quest;
    }

    public List<Todo> getTodos(SQLiteDatabase db) {

        List<Todo> todoList = new ArrayList<>();
        String sortOrder =  DbContract.Todos._ID + " ASC";

        Cursor c = db.query(
                DbContract.Todos.TABLE_NAME,          // The table to query
                TODO_PROJECTION,                      // The columns to return
                null,                                 // The columns for the WHERE clause
                null,                                 // The values for the WHERE clause
                null,                                 // don't group the rows
                null,                                 // don't filter by row groups
                sortOrder                             // The sort order
        );

        if (c != null) {
            c.moveToFirst();
            int count = c.getCount();

            for(int i =0; i < count; i++) {
                Todo todo = todoFromCursor(c);
                todoList.add(todo);
                c.moveToNext();
            }
            c.close();
        }

        return todoList;
    }

    public void markTodoAsDone(SQLiteDatabase db, int todoId){
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        String whereClause = DbContract.Todos._ID + "=?";
        ContentValues args = new ContentValues();
        args.put(DbContract.Todos.COLUMN_NAME_TODO_DONE, 1);
        args.put(DbContract.Todos.COLUMN_NAME_TODO_DONE_DATE, sdf.format(new Date()));
        db.update(DbContract.Todos.TABLE_NAME, args, whereClause, new String[]{Integer.toString(todoId)});
    }

    public void deleteTodo(SQLiteDatabase db, int todoId){
        String whereClause = DbContract.Todos._ID + "=?";
        db.delete(DbContract.Todos.TABLE_NAME, whereClause, new String[]{Integer.toString(todoId)});
    }

    public void updateLastActive(SQLiteDatabase db, long questId, String lastActive){
        String whereClause = DbContract.CharismaQuest.COLUMN_NAME_QUEST_ID + "=" + questId;
        ContentValues args = new ContentValues();
        args.put(DbContract.CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP, lastActive);
        db.update(DbContract.CharismaQuest.TABLE_NAME, args, whereClause, null);
    }


    private Quest fromCursor(Cursor c) {
        Quest quest = new Quest();
        quest.questId = c.getInt(0);
        quest.title = c.getString(1);
        quest.icon  = c.getString(2);
        quest.description  = c.getString(3);
        quest.info  = c.getString(4);
        quest.lastActive = c.getString(5);
        return quest;
    }

    private Todo todoFromCursor(Cursor c) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        Todo todo = new Todo();
        todo.id = c.getInt(0);
        todo.desc = c.getString(1);
        todo.type  = Todo.Type.valueOf(c.getString(2));
        todo.note  = c.getString(3);
        todo.done = c.getInt(5) == 1;
        try {
            String createDate = c.getString(4);
            String doneDate = c.getString(6);
            if(createDate != null) {
                todo.createDate = sdf.parse(createDate);
            }
            if (doneDate != null) {
                todo.doneDate = sdf.parse(doneDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todo;

    }


}