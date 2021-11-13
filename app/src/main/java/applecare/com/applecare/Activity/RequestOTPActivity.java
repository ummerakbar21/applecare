package applecare.com.applecare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import applecare.com.applecare.Model.OTP;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SharedPrepManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class RequestOTPActivity extends AppCompatActivity {
    private TextInputEditText etMobileNumber;
    private ConstraintLayout rootLayout;
    private TextView goBackToLoginLink;
    private Button getOTPBtn;
    private TextView signUpLink;

    private String mobileNumber;
    private ConstraintLayout activityOtp;
    private OTP otpObj = new OTP();
    private OtpView otpView;

    private SharedPrepManager sharedPrepManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_otp);
        sharedPrepManager = new SharedPrepManager(this);
        initializeViews();
        goToLoginActivity();
        goToSignUpActivity();
        getOTPBtnClick();


    }

    private void getOTPBtnClick() {
        getOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mobileNumber = etMobileNumber.getText().toString();
                if (TextUtils.isEmpty(mobileNumber)){
                    etMobileNumber.setError("Mobile number is required");
                    etMobileNumber.requestFocus();
                }
                else if(mobileNumber.length() <10)
                {
                    etMobileNumber.setError("Mobile No. cannot be less than 10 digits");
                    etMobileNumber.requestFocus();
                }
                else {
                    getOTP(mobileNumber);
                }
            }
        });
    }
    
    private void getOTP(String mobileNumber)
    {
        Retrofit retrofit = APIClient.getClient2();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        apiInterface.getOTP(mobileNumber).enqueue(new Callback<OTP>() {
            @Override
            public void onResponse(Call<OTP> call, Response<OTP> response) {
                activityOtp.setVisibility(View.VISIBLE);
                otpObj = response.body();
            }

            @Override
            public void onFailure(Call<OTP> call, Throwable t) {
                Toast.makeText(RequestOTPActivity.this, "Try again", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void initializeViews() {
        etMobileNumber = findViewById(R.id.mobile_login);
        getOTPBtn = findViewById(R.id.request_otp_button);
        rootLayout = findViewById(R.id.password_reset_root);
        goBackToLoginLink = findViewById(R.id.loginLink);
        signUpLink = findViewById(R.id.signUpLink);
        activityOtp = findViewById(R.id.otp_parent_view);
        otpView = findViewById(R.id.otp_view);
        setOtpViewListener();
    }
    protected void setOtpViewListener(){
        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override public void onOtpCompleted(String otp) {
                Retrofit retrofit = APIClient.getClient2();
                APIInterface apiInterface=retrofit.create(APIInterface.class);
                apiInterface.verifyOTP(otpObj.getDetails(),otp).enqueue(new Callback<OTP>() {
                    @Override
                    public void onResponse(Call<OTP> call, Response<OTP> response) {
                        if( response.body()!= null && response.body().getStatus().equalsIgnoreCase("Success") && response.body().getDetails().equalsIgnoreCase("OTP Matched")){
                            Toast.makeText(getApplicationContext(), "otp matched", Toast.LENGTH_SHORT).show();
                            sharedPrepManager.saveMobileNumber(mobileNumber);
                            activityOtp.setVisibility(View.GONE);
                            startActivity(new Intent(RequestOTPActivity.this,ChangePasswordActivity.class));
                            finish();

                        }else {
                            Toast.makeText(RequestOTPActivity.this, "Wrong OTP please retry", Toast.LENGTH_SHORT).show();

                        }
                        //Toast.makeText(SignUpActivity.this, "Verified", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Toast.makeText(RequestOTPActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        activityOtp.setVisibility(View.GONE);
                    }
                });

                // do Stuff
                //Log.d("onOtpCompleted=>", otp);
                // Toast.makeText(SignUpActivity.this, otp, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void goToSignUpActivity() {
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestOTPActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }
    private void goToLoginActivity() {
        goBackToLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestOTPActivity.this,LoginActivity.class));
                finish();
            }
        });
    }


}