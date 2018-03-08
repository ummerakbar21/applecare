package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import applecare.com.applecare.R;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences userTypeSharedPreferences;
    public String MyPREFERENCES = "UserPrefs";
    EditText phoneEditText;
    EditText passwordEditText;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        TextView signUpView = (TextView) findViewById(R.id.sign_up);
        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
        Button loginButton = (Button) findViewById(R.id.login_button);
        RadioGroup userTypeRadioGroup = (RadioGroup) findViewById(R.id.radio_grop);
        loginButton.setOnClickListener(onLogIn);
        userTypeSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userType=getResources().getString(R.string.farmer);
        userTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if (radioButton.getId() == R.id.farmer_radio_button) {

                    userType = getResources().getString(R.string.farmer);
                } else {

                    userType = getResources().getString(R.string.expert);
                }
                //editor.commit();
            }
        });

    }

    private View.OnClickListener onLogIn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            phoneEditText.setError(null);
            passwordEditText.setError(null);
            boolean error = false;
            if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
                phoneEditText.setError(getResources().getString(R.string.phone_required));
                error = true;
            }
            if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
                passwordEditText.setError(getResources().getString(R.string.password_required));
                error = true;
            }
            if (!error && userTypeSharedPreferences.getString("phone", "").equalsIgnoreCase(phoneEditText.getText().toString().trim())
                    && userTypeSharedPreferences.getString("password", "").equalsIgnoreCase(passwordEditText.getText().toString().trim())
                   /* && userTypeSharedPreferences.getString("type", "").equalsIgnoreCase(userType)*/) {
                SharedPreferences.Editor editor = userTypeSharedPreferences.edit();
                editor.putBoolean("login", true);
                editor.commit();
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            } else if (!error) {
                Toast.makeText(LoginActivity.this, "Wrong phone_number/password", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
