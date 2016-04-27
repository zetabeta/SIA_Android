package ch.checkbit.sia.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ch.checkbit.sia.helpers.Quest;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CharismaQuestsService extends Service {

//    ScheduledExecutorService scheduler =  Executors.newSingleThreadScheduledExecutor();

//    private Quest currentQuest;

    public void selectNextQuest() {




    }

    public CharismaQuestsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        scheduler.scheduleAtFixedRate
//                (new Runnable() {
//                    public void run() {
//                        // call service
//                    }
//                }, 0, 1, TimeUnit.DAYS);
//        return null;
        return null;
    }
}
