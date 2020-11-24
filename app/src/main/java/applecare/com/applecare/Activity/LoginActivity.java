package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import applecare.com.applecare.R;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button loginBtn;
    private TextView signUpLink;
    private FirebaseAuth mAuth;
    private String  email,password;
    SpotsDialog waitingDialog ;
    private ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Logging In...").build();
         initializeViews();
         loginClick();
        goToSignUpActivity();
    }

    private void goToSignUpActivity() {
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }
    private void loginClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromEditTexts();

                if (TextUtils.isEmpty(email)){
                    etEmail.setError("Email is required");
                }
                else  if (TextUtils.isEmpty(password)){
                    etPassword.setError("Password is required");
                }

                else if (password.length()<6 ){
                    etPassword.setError("Password should be atleast 6 characters");
                }
                else {
                    doLogin();
                }
            }
        });
    }

    private void doLogin() {
        waitingDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    waitingDialog.dismiss();
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(intent);
                 //   finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                waitingDialog.dismiss();
                Snackbar.make(rootLayout,""+e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void getTextFromEditTexts() {

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
    }

    private void initializeViews() {
        etEmail = findViewById(R.id.email_login);
        etPassword = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_join_button);
        rootLayout = findViewById(R.id.login_root);
        signUpLink = findViewById(R.id.signUpLink);
    }

}
