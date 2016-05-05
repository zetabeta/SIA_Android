package ch.checkbit.sia.activity;

import android.graphics.Typeface;
import android.os.Bundle;

import ch.checkbit.sia.R;

public class DailyTasksActivity extends SiaAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks);
        setupToolbar(getAssets(), R.id.daily_tasks_toolbar, R.id.daily_tasks_title, R.drawable.btn_daily_tasks_sml);
    }

}
