package applecare.com.applecare.Activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by ummer on 12/6/18.
 */



public class MyApplication extends Application {
    private static MyApplication singleton;


    @Override
    public void onCreate() {
        super.onCreate();
        /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HelveticaNeueItalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

    }

}