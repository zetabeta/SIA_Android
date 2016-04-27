package ch.checkbit.sia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import ch.checkbit.sia.db.daos.QuestDAOV1;
import ch.checkbit.sia.db.daos.TodoDAOV1;
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


    public SiaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        QuestDAOV1.createTables(db);
        TodoDAOV1.createTables(db);

        initializeQuestsData(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        QuestDAOV1.deleteTables(db); // TODO: migrate data from older database version
        TodoDAOV1.deleteTables(db); // TODO: migrate data from older database version

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void initializeQuestsData(SQLiteDatabase db) {
        for (Quest q: CharismaQuests.getQuests()) {
            QuestDAOV1.insertCharismaQuest(db, q.questId, q.icon, q.title, q.description, q.info);
        }
    }


    public long insertTodo(SQLiteDatabase db, String desc, String note, Todo.Type type){
        return TodoDAOV1.createNew(db, desc, note, type);
    }

    public List<Todo> getTodosByState(SQLiteDatabase db, Todo.State state) {

        return TodoDAOV1.getAllTodos(db, state);
    }

    public void markTodoAsDone(SQLiteDatabase db, int todoId) {
        TodoDAOV1.markAsDone(db, todoId);
    }

    public void deleteTodo(SQLiteDatabase db, int todoId) {
        TodoDAOV1.delete(db, todoId);
    }


    public List<Quest> getQuests(SQLiteDatabase db) {
        return QuestDAOV1.getAllQuests(db);
    }


    public Quest getQuestByTimestamp(SQLiteDatabase db, String lastActiveTimestamp) {
        return QuestDAOV1.getQuestByTimeStamp(db, lastActiveTimestamp);
    }

    public void updateLastActive(SQLiteDatabase db, long questId, String lastActive){
        QuestDAOV1.upLastActive(db, questId, lastActive);
    }

}