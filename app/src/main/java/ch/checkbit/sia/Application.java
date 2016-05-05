package ch.checkbit.sia;

import ch.checkbit.sia.layout.FontsOverride;

/**
 * SIA ch.checkbit.sia
 * <p/>
 * <p/>
 * Created by zeta on 05/05/16.
 */
public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/ClearSans-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/ClearSans-Light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/ClearSans-Light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/ClearSans-Light.ttf");
    }
}
