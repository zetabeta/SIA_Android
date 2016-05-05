package ch.checkbit.sia.activity;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 *
 * encapsulates some common behavior
 * <p/>
 * <p/>
 * Created by zeta on 05/05/16.
 */
public class SiaAbstractActivity extends AppCompatActivity {

    public void setupToolbar(AssetManager assets, int toolbarResId, int toolbarTextResId, int iconResource) {

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(toolbarResId);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) findViewById(toolbarTextResId);
        toolbarTitle.setTextSize(20);
        toolbarTitle.setAllCaps(true);
        toolbar.setLogo(iconResource);
        setSupportActionBar(toolbar);
    }
}
