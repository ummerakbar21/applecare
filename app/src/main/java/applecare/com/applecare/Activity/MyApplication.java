package applecare.com.applecare.Activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by ummer on 12/6/18.
 */

import applecare.com.applecare.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyApplication extends Application {
    private static MyApplication singleton;


    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HelveticaNeueItalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}