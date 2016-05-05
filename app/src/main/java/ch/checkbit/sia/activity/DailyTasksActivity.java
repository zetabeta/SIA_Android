package ch.checkbit.sia.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ch.checkbit.sia.R;
import ch.checkbit.sia.helpers.SiaConstants;

public class DailyTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks);
        setupToolbar();
    }

    private void setupToolbar() {
         /* font */
        final Typeface SIA_FONT = Typeface.createFromAsset(getAssets(), SiaConstants.FONT_HANDWRITTEN);

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.daily_tasks_toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.daily_tasks_title);
        toolbarTitle.setTypeface(SIA_FONT);
        toolbar.setLogo(R.drawable.btn_daily_tasks_sml);
        setSupportActionBar(toolbar);
    }


}
