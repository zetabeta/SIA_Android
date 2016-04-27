package ch.checkbit.sia.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ch.checkbit.sia.R;
import ch.checkbit.sia.db.SiaDbHelper;
import ch.checkbit.sia.helpers.Quest;
import ch.checkbit.sia.helpers.SiaConstants;

public class CharismaQuestsActivity extends AppCompatActivity {

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd";


    private SiaDbHelper dbHelper;
    private String timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charisma_quests);
        setupToolbar();


        dbHelper = new SiaDbHelper(getApplicationContext());

        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault());
        Date today = new Date();
        timestamp = sdf.format(today);
        Quest currentQuest = dbHelper.getQuestByTimestamp(dbHelper.getReadableDatabase(), timestamp);
        if(currentQuest == null) {
            currentQuest = selectNewQuest();
        }

        final Quest quest = currentQuest;
        drawCurrentQuest(quest);

    }

    private Quest selectNewQuest() {
        List<Quest> quests = dbHelper.getQuests(dbHelper.getReadableDatabase());
        long  questId = new Random(new Date().getTime()).nextInt(quests.size() - 1);
        Quest currentQuest = quests.get((int) questId);
        currentQuest.lastActive = timestamp;
        dbHelper.updateLastActive(dbHelper.getWritableDatabase(), currentQuest.questId, timestamp);
        return currentQuest;
    }


    private void drawCurrentQuest( final Quest quest) {

        final Typeface SIA_FONT_TXT = Typeface.createFromAsset(getAssets(), SiaConstants.FONT_TEXT);

         /* quest title */
        TextView titleView = (TextView) findViewById(R.id.quest_title);
        titleView.setTypeface(SIA_FONT_TXT);
        titleView.setText(quest.title);

         /* quest text */
        TextView questView = (TextView) findViewById(R.id.charisma_quest);
        questView.setTypeface(SIA_FONT_TXT);
        questView.setText(quest.description);

        /* quest icon */
        String uri = "@drawable/" + quest.icon;
        int questImageId = getResources().getIdentifier(uri, null, getPackageName());
        ImageView questImage = (ImageView) findViewById(R.id.charisma_quest_image);
        Drawable res = getResources().getDrawable(questImageId);
        questImage.setImageDrawable(res);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNextQuest(quest);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    private void setupToolbar() {
         /* font */
        final Typeface SIA_FONT = Typeface.createFromAsset(getAssets(), SiaConstants.FONT_HANDWRITTEN);

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.charisma_quest_title);
        toolbarTitle.setTypeface(SIA_FONT);
        toolbar.setLogo(R.drawable.btn_charisma_quests);
        setSupportActionBar(toolbar);
    }


    private void selectNextQuest(Quest currentQuest) {
        dbHelper.updateLastActive(dbHelper.getWritableDatabase(), currentQuest.questId, null);
        List<Quest> quests = dbHelper.getQuests(dbHelper.getReadableDatabase());
        long  questId = (currentQuest.questId + 1) % quests.size();
        Quest quest = quests.get((int) questId);
        quest.lastActive = timestamp;
        dbHelper.updateLastActive(dbHelper.getWritableDatabase(), questId, timestamp);
    }

}
