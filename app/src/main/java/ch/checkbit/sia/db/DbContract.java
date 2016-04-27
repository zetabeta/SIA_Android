package ch.checkbit.sia.db;

import android.provider.BaseColumns;

/**
 * Created by zeta on 07/01/16.
 */
public final class DbContract {

    public DbContract() {}

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

    // TODOs DB definition
    public static abstract class Todos implements BaseColumns {
        public static final String TABLE_NAME = "TODO";
        public static final String COLUMN_NAME_TODO_DESC = "todo_description";
        public static final String COLUMN_NAME_TODO_CREATE_DATE = "todo_create_date";
        public static final String COLUMN_NAME_TODO_NOTE = "todo_note";
        public static final String COLUMN_NAME_TODO_DONE = "todo_done";
        public static final String COLUMN_NAME_TODO_DONE_DATE = "todo_done_date";
        public static final String COLUMN_NAME_TODO_TYPE = "todo_type";
    }
}