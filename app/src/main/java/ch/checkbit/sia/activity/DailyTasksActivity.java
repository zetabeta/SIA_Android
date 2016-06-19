package ch.checkbit.sia.activity;

//import android.app.Fragment;
//import android.app.FragmentManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;


import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.checkbit.sia.R;

public class DailyTasksActivity extends FragmentActivity  implements DailyTasksFragment.OnFragmentInteractionListener {




    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 30;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager dayPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter dayPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks);

        // Instantiate a ViewPager and a PagerAdapter.
        dayPager = (ViewPager) findViewById(R.id.dayPager);
        dayPagerAdapter = new DailyTasksPagerAdapter(getSupportFragmentManager());
        dayPager.setAdapter(dayPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (dayPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            dayPager.setCurrentItem(dayPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class DailyTasksPagerAdapter extends FragmentStatePagerAdapter {
        public DailyTasksPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            DailyTasksFragment fragment= new DailyTasksFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
