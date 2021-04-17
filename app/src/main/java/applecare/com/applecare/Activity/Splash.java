package applecare.com.applecare.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;


import applecare.com.applecare.R;
import applecare.com.applecare.network.SessionManager;

public class Splash extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         sessionManager = SessionManager.getSessionManager(this);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.getUser() !=null){

                    Log.d("TAG", "getAccessToken: "+ sessionManager.getUser().getAccessToken());
                    startActivity( new Intent(Splash.this, MainActivity.class));
                    finish();
                }
                else {
                    startActivity( new Intent(Splash.this, SignUpActivity.class));
                    finish();
                }

            }
        },3000);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary
    }
}