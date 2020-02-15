package applecare.com.applecare.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;

import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

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
                //startActivity(intent);
                if(!userTypeSharedPreferences.contains("login")) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }else {


                    if (!userTypeSharedPreferences.getBoolean("login",false)) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);


                    }else{

                    }
                }
                // close this activity
                finish();
            }
        }, Constants.DELAY);



    }



}
