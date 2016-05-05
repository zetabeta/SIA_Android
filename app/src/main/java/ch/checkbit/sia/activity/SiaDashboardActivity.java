package ch.checkbit.sia.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ch.checkbit.sia.R;


public class SiaDashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sia_dashboard);

            /* TODOs */
        Button btnTodos = (Button) findViewById(R.id.btn_todos);

        btnTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TodoActivity.class);
                startActivity(i);
            }
        });

        /* Daily tasks */
        Button btnDailyTasks = (Button) findViewById(R.id.btn_daily_tasks);

        btnDailyTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DailyTasksActivity.class);
                startActivity(i);
            }
        });

        /* Charisma quests */
        Button btnCharismaQuests = (Button) findViewById(R.id.btn_charisma_quests);

        btnCharismaQuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CharismaQuestsActivity.class);
                startActivity(i);
            }
        });

        /* Voice exercises */
        Button btnVoiceExercises = (Button) findViewById(R.id.btn_voice_exercises);

        /* Future */
        Button btnFuture = (Button) findViewById(R.id.btn_future);

        /* Superpowers */
        Button btnSuperpowers = (Button) findViewById(R.id.btn_superpowers);

    }

}
