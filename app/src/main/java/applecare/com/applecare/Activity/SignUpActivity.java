package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Constants;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText emailField;
    private TextInputEditText nameField;
    private TextInputEditText passwordField;
    private TextInputEditText confirmPwdField;
    private TextInputEditText districtField;
    private Button signUpBtn;
    private TextView alreadySignUpLink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeView();

    }

    private void initializeView() {
        emailField= findViewById(R.id.email_sign_up);
        nameField= findViewById(R.id.name_sign_up);
        passwordField= findViewById(R.id.password_sign_up);
        confirmPwdField= findViewById(R.id.confirm_password_sign_up);
        districtField= findViewById(R.id.district_sign_up);
        signUpBtn= findViewById(R.id.signup_join_button);
        signUpBtn.setOnClickListener(this);
        alreadySignUpLink= findViewById(R.id.alreadyJoin_signUp);
        alreadySignUpLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up_button:
                break;
            case R.id.alreadyJoin_signUp:
                break;
            default:
                break;
        }

    }

}
