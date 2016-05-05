package ch.checkbit.sia.activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import ch.checkbit.sia.helpers.SiaConstants;

/**
 *
 * encapsulates some common behavior
 * <p/>
 * <p/>
 * Created by zeta on 05/05/16.
 */
public class SiaAbstractActivity extends AppCompatActivity {

    public void setupToolbar(AssetManager assets, int toolbarResId, int toolbarTextResId, int iconResource) {

         /* font */
        //final Typeface SIA_FONT = Typeface.createFromAsset(assets, SiaConstants.FONT_HANDWRITTEN);

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(toolbarResId);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) findViewById(toolbarTextResId);
        //toolbarTitle.setTypeface(SIA_FONT);
        toolbarTitle.setTextSize(20);
        toolbarTitle.setAllCaps(true);
        toolbar.setLogo(iconResource);
        setSupportActionBar(toolbar);
    }
}
