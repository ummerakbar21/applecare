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

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Constants;


public class SignUpActivity extends AppCompatActivity {
    SharedPreferences userTypeSharedPreferences;
    public String MyPREFERENCES = "UserPrefs" ;
    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText rptPasswrdEditText;
    String userType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RadioGroup userTypeRadioGroup=(RadioGroup)findViewById(R.id.radio_grop);
        RadioButton farmerRadioButton=(RadioButton)findViewById(R.id.farmer_radio_button);
        RadioButton expertRadioButton=(RadioButton)findViewById(R.id.expert_radio_button);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        rptPasswrdEditText = (EditText) findViewById(R.id.confirm_password_edit_text);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button signUpButton=(Button)findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(onSignUp);
        userTypeSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        userTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radioButton=(RadioButton)findViewById(i);
                if(radioButton.getId()==R.id.farmer_radio_button){
                    userType=radioButton.getText().toString();
                }else{
                    userType=radioButton.getText().toString();
                }}
        });



    }
     private View.OnClickListener onSignUp=new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             final SharedPreferences.Editor editor = userTypeSharedPreferences.edit();
             Intent mainIntent = new Intent(view.getContext(), MainActivity.class);
             mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(mainIntent);
      /*       nameEditText.setError(null);
             emailEditText.setError(null);
             phoneEditText.setError(null);
             passwordEditText.setError(null);
             rptPasswrdEditText.setError(null);
             boolean error = false;
             if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                 nameEditText.setError(getResources().getString(R.string.name_required));
                 error = true;
             }
             if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
                 phoneEditText.setError(getResources().getString(R.string.phone_required));
                 error = true;
             } else if (!phoneEditText.getText().toString().trim().matches(Constants.REGEXP_PHONE)&& phoneEditText.getText().toString().trim().length()!=10) {
                 phoneEditText.setError(getResources().getString(R.string.invalid_phone));
                 error = true;
             }
             if (!TextUtils.isEmpty(emailEditText.getText().toString())) {
                 if (!emailEditText.getText().toString().trim().matches(Constants.REGEXP_EMAIL)) {
                     emailEditText.setError(getResources().getString(R.string.inavlid_email));
                     error = true;
                 }
             }
             if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
                 passwordEditText.setError(getResources().getString(R.string.password_required));
                 error = true;
             }
             if (TextUtils.isEmpty(rptPasswrdEditText.getText().toString())) {
                 rptPasswrdEditText.setError(getResources().getString(R.string.rpt_password_required));
                 error = true;
             }
             if (!rptPasswrdEditText.getText().toString().matches(passwordEditText.getText().toString())) {
                 rptPasswrdEditText.setError(getResources().getString(R.string.password_mismatch));
                 error = true;
             }
             if (!error) {
                 if(TextUtils.isEmpty(userType)||userType.equalsIgnoreCase(getResources().getString(R.string.farmer)))
                     editor.putString("type",getResources().getString(R.string.farmer));
                 else if(userType.equalsIgnoreCase(getResources().getString(R.string.expert)))
                     editor.putString("type",getResources().getString(R.string.expert));

                 editor.putString("phone",phoneEditText.getText().toString().trim());
                 editor.putString("password",passwordEditText.getText().toString().trim());
                 editor.putBoolean("login",true);
                 editor.commit();
                 Intent mainIntent = new Intent(view.getContext(), MainActivity.class);
                 mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(mainIntent);
             }*/
         }
     };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
