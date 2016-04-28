package ch.checkbit.sia.helpers;

import java.util.Date;

/**
 * Created by zeta on 21/02/16.
 */
public class Todo{

    public Integer id;
    public String desc;
    public Date createDate;
    public String note;
    public Boolean done;
    public Date doneDate;
    public Type type;


    public enum Type {
        DELEGATE,   // at-sign @
        LONG_TERM,  // exclamation mark
        SHORT_TERM  // check
    }

    public enum State {ONGOING, COMPLETED}
}
