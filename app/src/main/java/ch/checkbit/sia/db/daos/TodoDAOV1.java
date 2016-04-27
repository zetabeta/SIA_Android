package ch.checkbit.sia.db.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.checkbit.sia.helpers.Todo;

/**
 * Created by raymoon on 4/27/16.
 */
public class TodoDAOV1 extends AbstractDAO {

    public static abstract class Todos implements BaseColumns {
        public static final String TABLE_NAME = "TODO";
        public static final String COLUMN_NAME_TODO_DESC = "todo_description";
        public static final String COLUMN_NAME_TODO_CREATE_DATE = "todo_create_date";
        public static final String COLUMN_NAME_TODO_NOTE = "todo_note";
        public static final String COLUMN_NAME_TODO_DONE = "todo_done";
        public static final String COLUMN_NAME_TODO_DONE_DATE = "todo_done_date";
        public static final String COLUMN_NAME_TODO_TYPE = "todo_type";
    }

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TODO_TABLE =
            "CREATE TABLE " + Todos.TABLE_NAME + " (" +
                    Todos._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_CREATE_DATE + getSpace() + getTextType() + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_DESC + getSpace() + getTextType() + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_DONE + getSpace() + getIntegerType() + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_DONE_DATE + getSpace() + getTextType() + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_TYPE + getSpace() + getTextType() + COMMA_SEP +
                    Todos.COLUMN_NAME_TODO_NOTE + getSpace() + getTextType() +
                    " )";



    private static final String[] TODO_PROJECTION = {
            Todos._ID,
            Todos.COLUMN_NAME_TODO_DESC,
            Todos.COLUMN_NAME_TODO_TYPE,
            Todos.COLUMN_NAME_TODO_NOTE,
            Todos.COLUMN_NAME_TODO_CREATE_DATE,
            Todos.COLUMN_NAME_TODO_DONE,
            Todos.COLUMN_NAME_TODO_DONE_DATE
    };

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-DD HH:mm:ss.SSS";

    private static final String SQL_DELETE_TODOS_TABLE =
            "DROP TABLE IF EXISTS " + Todos.TABLE_NAME;


    public static void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    public static void deleteTables(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_TODOS_TABLE);
    }

    public static long createNew(SQLiteDatabase db, String desc, String note, Todo.Type type) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        ContentValues values = new ContentValues();
        values.put(Todos.COLUMN_NAME_TODO_CREATE_DATE, sdf.format(new Date()));
        values.put(Todos.COLUMN_NAME_TODO_DESC, desc);
        values.put(Todos.COLUMN_NAME_TODO_NOTE, note);
        values.put(Todos.COLUMN_NAME_TODO_TYPE, type.name());

        return db.insert(
                Todos.TABLE_NAME,
                null,
                values);
    }

    public static List<Todo> getAllTodos(SQLiteDatabase db) {

        List<Todo> todoList = new ArrayList<>();
        String sortOrder = Todos._ID + " ASC";

        Cursor c = db.query(
                Todos.TABLE_NAME,          // The table to query
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
        String whereClause = Todos._ID + "=?";
        db.delete(Todos.TABLE_NAME, whereClause, new String[]{Integer.toString(todoId)});
    }

    public static void markAsDone(SQLiteDatabase db, int todoId) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        String whereClause = Todos._ID + "=?";
        ContentValues args = new ContentValues();
        args.put(Todos.COLUMN_NAME_TODO_DONE, 1);
        args.put(Todos.COLUMN_NAME_TODO_DONE_DATE, sdf.format(new Date()));
        db.update(Todos.TABLE_NAME, args, whereClause, new String[]{Integer.toString(todoId)});
    }
}
