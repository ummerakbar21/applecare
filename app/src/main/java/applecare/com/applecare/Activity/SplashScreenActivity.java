package applecare.com.applecare.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Constants;

public class SplashScreenActivity extends AppCompatActivity {
    SharedPreferences userTypeSharedPreferences;
    public String MyPREFERENCES = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userTypeSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                if(!userTypeSharedPreferences.contains("login")) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }else {


                    if (!userTypeSharedPreferences.getBoolean("login",false)) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);


                    }else{
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
                // close this activity
                finish();
            }
        }, Constants.DELAY);



    }



}
