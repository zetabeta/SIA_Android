package ch.checkbit.sia.db.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ch.checkbit.sia.helpers.DailyTask;
import ch.checkbit.sia.helpers.DailyTaskCategory;
import ch.checkbit.sia.helpers.DailyTaskType;
import ch.checkbit.sia.helpers.Todo;


public class DailyTaskDAOV1 extends AbstractDAO {


    public static abstract class DailyTasks implements BaseColumns {
        public static final String TABLE_NAME = "DAILY_TASKS";
        public static final String COLUMN_NAME_TASK = "task";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_FOR_DATE = "for_date";
        public static final String COLUMN_NAME_CREATED = "created";
        public static final String COLUMN_NAME_COMPLETED = "completed";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_TODO_ID = "todo_id";
    }

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TODO_TABLE =
            "CREATE TABLE " + DailyTasks.TABLE_NAME + " (" +
                    DailyTasks._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_TASK + getSpace() + getTextType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_CATEGORY + getSpace() + getTextType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_FOR_DATE + getSpace() + getTextType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_CREATED + getSpace() + getTextType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_COMPLETED + getSpace() + getTextType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_TODO_ID + getSpace() + getIntegerType() + COMMA_SEP +
                    DailyTasks.COLUMN_NAME_TYPE + getSpace() + getTextType() +
                    " )";


    private static final String[] DAILY_TASK_PROJECTION = {
            DailyTasks._ID,
            DailyTasks.COLUMN_NAME_TASK,
            DailyTasks.COLUMN_NAME_CATEGORY,
            DailyTasks.COLUMN_NAME_FOR_DATE,
            DailyTasks.COLUMN_NAME_CREATED,
            DailyTasks.COLUMN_NAME_COMPLETED,
            DailyTasks.COLUMN_NAME_TYPE,
            DailyTasks.COLUMN_NAME_TODO_ID
    };

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-DD HH:mm:ss.SSS";

    private static final String SQL_DELETE_DAILY_TASKS_TABLE =
            "DROP TABLE IF EXISTS " + DailyTasks.TABLE_NAME;


    public static void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    public static void deleteTables(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_DAILY_TASKS_TABLE);
    }

    public static long createNew(SQLiteDatabase db, String task, Date forDate, DailyTaskCategory category, DailyTaskType type, Long todoId) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        ContentValues values = new ContentValues();
        values.put(DailyTasks.COLUMN_NAME_CREATED, sdf.format(new Date()));
        values.put(DailyTasks.COLUMN_NAME_TASK, task);
        values.put(DailyTasks.COLUMN_NAME_CATEGORY, category.name());
        values.put(DailyTasks.COLUMN_NAME_FOR_DATE, sdf.format(forDate));
        values.put(DailyTasks.COLUMN_NAME_TYPE, type.name());
        values.put(DailyTasks.COLUMN_NAME_TODO_ID, todoId);

        return db.insert(
                DailyTasks.TABLE_NAME,
                null,
                values);
    }

    public static Map<DailyTaskCategory, List<DailyTask>> getDailyTasks(SQLiteDatabase db, Date forDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        Cursor c = db.rawQuery("select " + getFields() + " from " + DailyTasks.TABLE_NAME + " WHERE " + DailyTasks.COLUMN_NAME_FOR_DATE + " = ", new String[]{sdf.format(forDate)}
        );

        Map<DailyTaskCategory, List<DailyTask>> dailyTasks = new HashMap<>();
        dailyTasks.put(DailyTaskCategory.HATE, new ArrayList<DailyTask>());
        dailyTasks.put(DailyTaskCategory.FUTURE, new ArrayList<DailyTask>());
        dailyTasks.put(DailyTaskCategory.LEARNING, new ArrayList<DailyTask>());
        dailyTasks.put(DailyTaskCategory.HEALTH, new ArrayList<DailyTask>());
        dailyTasks.put(DailyTaskCategory.FUN, new ArrayList<DailyTask>());
        List<DailyTask> tasks = extractResults(c);

        for (DailyTask dt : tasks) {
            dailyTasks.get(dt.category).add(dt);
        }

        return dailyTasks;
    }

    private static String getFields() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < DAILY_TASK_PROJECTION.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(DAILY_TASK_PROJECTION[i]);
        }

        return builder.toString();

    }

    @NonNull
    private static List<DailyTask> extractResults(Cursor c) {
        List<DailyTask> dailyTasks = new ArrayList<>();
        if (c != null) {
            c.moveToFirst();
            int count = c.getCount();

            for (int i = 0; i < count; i++) {
                DailyTask dailyTask = dailyTaskFromCursor(c);
                dailyTasks.add(dailyTask);
                c.moveToNext();
            }
            c.close();
        }
        return dailyTasks;
    }

    private static DailyTask dailyTaskFromCursor(Cursor c) {
        DailyTask dt = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
            dt = new DailyTask();
            dt.id = c.getInt(0);
            dt.task = c.getString(1);
            dt.category = DailyTaskCategory.valueOf(c.getString(2));
            dt.forDate = sdf.parse(c.getString(3));

            String createDate = c.getString(4);
            String completedDate = c.getString(5);
            if (createDate != null) {
                dt.created = sdf.parse(createDate);
            }
            if (completedDate != null) {
                dt.completed = sdf.parse(completedDate);
            }

            dt.type = DailyTaskType.FROM_TODO.valueOf(c.getString(6));
            dt.todoId = c.getLong(7);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;

    }

    public static void delete(SQLiteDatabase db, int dailyTaskId) {
        String whereClause = DailyTasks._ID + "=?";
        db.delete(DailyTasks.TABLE_NAME, whereClause, new String[]{Integer.toString(dailyTaskId)});
    }

    public static void update(SQLiteDatabase db, int dailyTaskId, String task, boolean completed ) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault());
        String whereClause = DailyTasks._ID + "=?";
        ContentValues args = new ContentValues();
        if(task != null) {
            args.put(DailyTasks.COLUMN_NAME_TASK, task);
        }
        String completedDate = completed? sdf.format(new Date()): null;
        args.put(DailyTasks.COLUMN_NAME_COMPLETED, completedDate);
        db.update(DailyTasks.TABLE_NAME, args, whereClause, new String[]{Integer.toString(dailyTaskId)});
    }
}
