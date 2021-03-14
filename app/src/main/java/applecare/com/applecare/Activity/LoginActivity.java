package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
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

import com.google.firebase.messaging.FirebaseMessaging;

import applecare.com.applecare.Model.LoginUser;
import applecare.com.applecare.Model.User;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button loginBtn;
    private TextView signUpLink;

    private String  email,password;
    SpotsDialog waitingDialog ;
    private ConstraintLayout rootLayout;
    private SessionManager sessionManager;
    private String firebaseToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

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
                   logIn();
                }
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
    private  void  logIn(){
        waitingDialog.show();
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        sessionManager = SessionManager.getSessionManager(this);
        LoginUser loginUser = new LoginUser(email,password);
        apiInterface.loginUser(loginUser,sessionManager.getAuthTokenForSignUP()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: "+response);
                waitingDialog.dismiss();
                if(response.body() != null){
                    sessionManager.saveConfigData(response.body());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(rootLayout,""+"Something went wrong",Snackbar.LENGTH_LONG).show();

                Log.d("TAG", "onFailure: "+"fail");

            }
        });


    }
    private  void getFirebaseToken(){

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            // Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        firebaseToken = task.getResult();


                    }
                });

    }

}
