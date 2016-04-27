package ch.checkbit.sia.db.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import ch.checkbit.sia.helpers.Quest;

/**
 * Created by raymoon on 4/27/16.
 */
public class QuestDAOV1 extends AbstractDAO {


    // charisma quests DB definition
    public static abstract class CharismaQuest implements BaseColumns {
        public static final String TABLE_NAME = "CHARISMA_QUESTS";
        public static final String COLUMN_NAME_QUEST_ID = "quest_id";
        public static final String COLUMN_NAME_QUEST_TITLE = "quest_title";
        public static final String COLUMN_NAME_QUEST_ICON = "quest_icon";
        public static final String COLUMN_NAME_QUEST_DESC = "quest_desc";
        public static final String COLUMN_NAME_QUEST_INFO = "quest_info";
        public static final String COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP = "quest_last_active_timestamp";
    }

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-DD HH:mm:ss.SSS";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_CHARISMA_QUESTS_TABLE =
            "CREATE TABLE " + CharismaQuest.TABLE_NAME + " (" +
                    CharismaQuest._ID + " INTEGER PRIMARY KEY," +
                    CharismaQuest.COLUMN_NAME_QUEST_ID + getSpace() + getIntegerType() + COMMA_SEP +
                    CharismaQuest.COLUMN_NAME_QUEST_TITLE + getSpace() + getTextType() + COMMA_SEP +
                    CharismaQuest.COLUMN_NAME_QUEST_ICON + getSpace() + getTextType() + COMMA_SEP +
                    CharismaQuest.COLUMN_NAME_QUEST_DESC + getSpace() + getTextType() + COMMA_SEP +
                    CharismaQuest.COLUMN_NAME_QUEST_INFO + getSpace() + getTextType() + COMMA_SEP +
                    CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + " " + getTextType() +
                    " )";


    private static final String SQL_DELETE_CHARISMA_QUESTS_TABLE =
            "DROP TABLE IF EXISTS " + CharismaQuest.TABLE_NAME;


    private static final String[] CHARISMA_QUESTS_PROJECTION = {
            CharismaQuest.COLUMN_NAME_QUEST_ID,
            CharismaQuest.COLUMN_NAME_QUEST_TITLE,
            CharismaQuest.COLUMN_NAME_QUEST_ICON,
            CharismaQuest.COLUMN_NAME_QUEST_DESC,
            CharismaQuest.COLUMN_NAME_QUEST_INFO,
            CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP
    };

    public static void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHARISMA_QUESTS_TABLE);
    }

    public static void deleteTables(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_CHARISMA_QUESTS_TABLE);
    }


    public static long insertCharismaQuest(SQLiteDatabase db, long questId, String icon, String title, String desc, String info) {
        ContentValues values = new ContentValues();
        values.put(CharismaQuest.COLUMN_NAME_QUEST_ID, questId);
        values.put(CharismaQuest.COLUMN_NAME_QUEST_ICON, icon);
        values.put(CharismaQuest.COLUMN_NAME_QUEST_TITLE, title);
        values.put(CharismaQuest.COLUMN_NAME_QUEST_DESC, desc);
        values.put(CharismaQuest.COLUMN_NAME_QUEST_INFO, info);

        return db.insert(
                CharismaQuest.TABLE_NAME,
                null,
                values);
    }

    public static List<Quest> getAllQuests(SQLiteDatabase db) {

        List<Quest> quests = new ArrayList<>();
        String sortOrder =
                CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + " DESC";

        Cursor c = db.query(
                CharismaQuest.TABLE_NAME,  // The table to query
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

    public static Quest getQuestByTimeStamp(SQLiteDatabase db, String lastActiveTimestamp) {

        Quest quest = null;

        Cursor c = db.query(
                CharismaQuest.TABLE_NAME,                                        // The table to query
                CHARISMA_QUESTS_PROJECTION,                                                 // The columns to return
                CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP + "=?",    // The columns for the WHERE clause
                new String[]{lastActiveTimestamp},                                          // The values for the WHERE clause
                null,                                                                       // don't group the rows
                null,                                                                       // don't filter by row groups
                null                                                                        // The sort order
        );

        if (c != null) {

            if (c.getCount() > 0) {
                c.moveToFirst();
                quest = fromCursor(c);
            }
            c.close();
        }
        return quest;
    }

    private static Quest fromCursor(Cursor c) {
        Quest quest = new Quest();
        quest.questId = c.getInt(0);
        quest.title = c.getString(1);
        quest.icon = c.getString(2);
        quest.description = c.getString(3);
        quest.info = c.getString(4);
        quest.lastActive = c.getString(5);
        return quest;
    }

    public static void upLastActive(SQLiteDatabase db, long questId, String lastActive) {
        String whereClause = CharismaQuest.COLUMN_NAME_QUEST_ID + "=" + questId;
        ContentValues args = new ContentValues();
        args.put(CharismaQuest.COLUMN_NAME_QUEST_LAST_ACTIVE_TIMESTAMP, lastActive);
        db.update(CharismaQuest.TABLE_NAME, args, whereClause, null);
    }
}
