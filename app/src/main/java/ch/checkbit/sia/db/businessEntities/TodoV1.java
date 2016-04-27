package ch.checkbit.sia.db.businessEntities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.checkbit.sia.db.DbContract;
import ch.checkbit.sia.helpers.Todo;

/**
 * Created by raymoon on 4/27/16.
 */
public class TodoV1 {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

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


    private static final String[] TODO_PROJECTION = {
            DbContract.Todos._ID,
            DbContract.Todos.COLUMN_NAME_TODO_DESC,
            DbContract.Todos.COLUMN_NAME_TODO_TYPE,
            DbContract.Todos.COLUMN_NAME_TODO_NOTE,
            DbContract.Todos.COLUMN_NAME_TODO_CREATE_DATE,
            DbContract.Todos.COLUMN_NAME_TODO_DONE,
            DbContract.Todos.COLUMN_NAME_TODO_DONE_DATE
    };

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-DD HH:mm:ss.SSS";

    private static final String SQL_DELETE_TODOS_TABLE =
            "DROP TABLE IF EXISTS " + DbContract.Todos.TABLE_NAME;


    public static void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    public static void deleteTables(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_TODOS_TABLE);
    }

    public static long createNew(SQLiteDatabase db, String desc, String note, Todo.Type type) {
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

    public static List<Todo> getAllTodos(SQLiteDatabase db) {

        List<Todo> todoList = new ArrayList<>();
        String sortOrder = DbContract.Todos._ID + " ASC";

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

            for (int i = 0; i < count; i++) {
                Todo todo = todoFromCursor(c);
                todoList.add(todo);
                c.moveToNext();
            }
            c.close();
        }

        return todoList;
    }


    private static Todo todoFromCursor(Cursor c) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        Todo todo = new Todo();
        todo.id = c.getInt(0);
        todo.desc = c.getString(1);
        todo.type = Todo.Type.valueOf(c.getString(2));
        todo.note = c.getString(3);
        todo.done = c.getInt(5) == 1;
        try {
            String createDate = c.getString(4);
            String doneDate = c.getString(6);
            if (createDate != null) {
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

    public static void delete(SQLiteDatabase db, int todoId) {
        String whereClause = DbContract.Todos._ID + "=?";
        db.delete(DbContract.Todos.TABLE_NAME, whereClause, new String[]{Integer.toString(todoId)});
    }

    public static void markAsDone(SQLiteDatabase db, int todoId) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        String whereClause = DbContract.Todos._ID + "=?";
        ContentValues args = new ContentValues();
        args.put(DbContract.Todos.COLUMN_NAME_TODO_DONE, 1);
        args.put(DbContract.Todos.COLUMN_NAME_TODO_DONE_DATE, sdf.format(new Date()));
        db.update(DbContract.Todos.TABLE_NAME, args, whereClause, new String[]{Integer.toString(todoId)});
    }
}
