package ch.checkbit.sia.db.daos;

/**
 * Created by raymoon on 4/27/16.
 */
public abstract class AbstractDAO {

    protected static String getTextType() {
        return "TEXT";
    }

    protected static String getIntegerType() {
        return " INTEGER";
    }

    protected static String getSpace() {
        return " ";
    }
}
