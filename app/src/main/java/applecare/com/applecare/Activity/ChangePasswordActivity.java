package applecare.com.applecare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import applecare.com.applecare.Model.LoginUser;
import applecare.com.applecare.Model.User;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import applecare.com.applecare.network.SharedPrepManager;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextInputEditText etPassword,etConfirmPassword;
    private ConstraintLayout rootLayout;
    private TextView goBackToLoginLink;
    private Button changePasswordBtn;
    private TextView signUpLink;

    private String password,confirmPassword;

    public SharedPrepManager sharedPrepManager;
    SpotsDialog waitingDialog ;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Resetting Password...").build();
        sharedPrepManager = new SharedPrepManager(this);
        setContentView(R.layout.activity_change_password);
        initializeViews();
        changePasswordBtnClick();

    }

    private void initializeViews()
    {
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        changePasswordBtn = findViewById(R.id.change_password_button);
        rootLayout = findViewById(R.id.change_password_root);

    }

    private void changePasswordBtnClick() {
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(password)){
                    etPassword.setError("Password Cannot Be Empty");
                    etPassword.requestFocus();
                }

                else if(TextUtils.isEmpty(confirmPassword))
                {
                    etConfirmPassword.setError("Confirm Password Cannot Be Empty");
                    etConfirmPassword.requestFocus();
                }
                else if(! password.equals(confirmPassword))
                {
                    etConfirmPassword.setError("Password and Confirm Password Mismatch");
                    etConfirmPassword.requestFocus();
                }
                else {
                   changePassword(sharedPrepManager.getSavedMobileNumber(), password);
                }
            }
        });
    }

    private void changePassword(String mobileNumber,String newPassword){
        //call api to change password of user
        waitingDialog.show();
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        sessionManager = SessionManager.getSessionManager(this);
        apiInterface.resetPassword(mobileNumber,password,sessionManager.getAuthTokenForSignUP()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: "+response);
                waitingDialog.dismiss();
                if(response.body() != null){
                    sessionManager.saveConfigData(response.body());
                    Toast.makeText(getApplicationContext(), "Password Changed Sucessfully", Toast.LENGTH_LONG).show();
                    sharedPrepManager.remove("mobileNumber");
                    Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(rootLayout,""+"Something went wrong",Snackbar.LENGTH_LONG).show();
                sharedPrepManager.remove("mobileNumber");
                Log.d("TAG", "onFailure: "+"fail");

            }
        });

    }
}