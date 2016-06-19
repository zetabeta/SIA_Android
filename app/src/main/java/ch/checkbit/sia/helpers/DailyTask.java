package ch.checkbit.sia.helpers;

import java.util.Date;

/**
 * SIA ch.checkbit.sia.helpers
 * <p/>
 * <p/>
 * Created by zeta on 19/06/16.
 */
public class DailyTask {
    public long id;
    public String task;
    public DailyTaskCategory category;
    public Date forDate;
    public Date created;
    public Date completed;
    public DailyTaskType type;
    public long todoId;
}
