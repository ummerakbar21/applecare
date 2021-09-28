package applecare.com.applecare.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import applecare.com.applecare.Model.OTP;
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


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ConstraintLayout rootLayout;
    private TextInputEditText mobileField;
    private TextInputEditText nameField;
    private TextInputEditText passwordField;
    private TextInputEditText confirmPwdField;
    private TextInputEditText districtField;
    private Button signUpBtn;
    private TextView alreadySignUpLink;
    private String name;
    private String mobileNumber;
    private String password;
    private String confirmPassword;

    private String district;
    SpotsDialog waitingDialog ;
    private SessionManager sessionManager;
    private String firebaseToken;
    private OtpView otpView;
    private ImageView closeIcon;
    private ConstraintLayout activityOtp;
    private OTP otpObj = new OTP();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Registering...").build();
        initializeView();
        getFirebaseToken();

    }

    private void initializeView() {

        rootLayout = findViewById(R.id.register_root);
        mobileField = findViewById(R.id.mobile_sign_up);
        nameField= findViewById(R.id.name_sign_up);
        passwordField= findViewById(R.id.password_sign_up);
        confirmPwdField= findViewById(R.id.confirm_password_sign_up);
        districtField= findViewById(R.id.district_sign_up);
        signUpBtn= findViewById(R.id.signup_join_button);
        signUpBtn.setOnClickListener(this);
        alreadySignUpLink= findViewById(R.id.alreadyJoin_signUp);
        alreadySignUpLink.setOnClickListener(this);
        otpView = findViewById(R.id.otp_view);
        closeIcon = findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(this);
        activityOtp = findViewById(R.id.otp_parent_view);
        setOtpViewListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup_join_button:
                signUpUser();
                break;
            case R.id.alreadyJoin_signUp:
                goToLoginActivity();
                break;
            case R.id.close_icon:
                activityOtp.setVisibility(View.GONE);
                break;
                 default:
                break;
        }

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

                           signUpUserAfterVerification();
                           activityOtp.setVisibility(View.GONE);

                        }else {
                            Toast.makeText(SignUpActivity.this, "Wrong OTP please retry", Toast.LENGTH_SHORT).show();

                        }
                        //Toast.makeText(SignUpActivity.this, "Verified", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        activityOtp.setVisibility(View.GONE);
                    }
                });

                // do Stuff
                //Log.d("onOtpCompleted=>", otp);
               // Toast.makeText(SignUpActivity.this, otp, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLoginActivity() {
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }

    private void signUpUser() {
        getTextFromEditTexts();

        if (TextUtils.isEmpty(mobileNumber)){
            mobileField.setError("Mobile number is required");
        }

       else if (TextUtils.isEmpty(name)){
            nameField.setError("FirsName is Required");

        }
        else if (!name.isEmpty() &&name.length()<4){
            nameField.setError("Name should be atleast 4 characters");
        }

        else    if (TextUtils.isEmpty(password)){
            passwordField.setError("Password is required");
        }
        else   if (TextUtils.isEmpty(confirmPassword)){
            confirmPwdField.setError("Confirm password is required");
        }

        else if (password.length()<6 ){
            passwordField.setError("Password should be atleast 6 characters");
        }
        else   if (!password.equals(confirmPassword)){
            confirmPwdField.setError("Passwords does not match");
        }
        else if (TextUtils.isEmpty(district)){
            districtField.setError("District is Required");

        }
        else {

            registerUser();
        }

    }

    private void getTextFromEditTexts() {
        name = nameField.getText().toString();
        mobileNumber = mobileField.getText().toString();
        password = passwordField.getText().toString();
        confirmPassword = confirmPwdField.getText().toString();
        district = districtField.getText().toString();
    }

   /* private void registerUser() {
        waitingDialog.show();
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
//                            mName.setText("");
//                            mPhoneNumber.setText("");
//                            mEmail.setText("");
//                            mSemester.setText("");
//                            mRollNo.setText("");
//                            mPassword.setText("");
//                            mConfirmPassword.setText("");
                    waitingDialog.dismiss();
                    Snackbar.make(rootLayout,"This User is already registered",Snackbar.LENGTH_LONG).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            User signUpUser = new User(email,name,password,district,"","","");
                            databaseReference.child(mAuth.getUid()).setValue(signUpUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    waitingDialog.dismiss();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // waitingDialog.dismiss();
                                    Snackbar.make(rootLayout,""+e,Snackbar.LENGTH_SHORT).show();
                                //    Log.d("error",""+e);
                                    nameField.setText("");
                                    emailField.setText("");
                                    passwordField.setText("");
                                    confirmPwdField.setText("");
                                    districtField.setText("");

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          //  waitingDialog.dismiss();
                            nameField.setText("");
                            emailField.setText("");
                            passwordField.setText("");
                            confirmPwdField.setText("");
                            districtField.setText("");
                            Snackbar.make(rootLayout,""+e.getMessage(),Snackbar.LENGTH_LONG).show();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              //  waitingDialog.dismiss();
                Snackbar.make(rootLayout,"Something went wrong",Snackbar.LENGTH_LONG).show();

            }
        });


    }*/



    private  void  registerUser(){
       // activityOtp.setVisibility(View.VISIBLE);
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
                Toast.makeText(SignUpActivity.this, "Try again", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void signUpUserAfterVerification(){

        waitingDialog.show();
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        sessionManager = SessionManager.getSessionManager(this);
        User signUpUser = new User(mobileNumber,name,"",password,district,"user",firebaseToken);
        apiInterface.userSignLogin(signUpUser,sessionManager.getAuthTokenForSignUP()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: "+response);
                waitingDialog.dismiss();
                if(response.body() != null){
                    sessionManager.saveConfigData(response.body());
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                if(response.errorBody().toString().equals("User with this phone number/username already exists")){
                    mobileField.setError("User already exists");
                    Snackbar.make(rootLayout,""+"User with this email already exists",Snackbar.LENGTH_LONG).show();


                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                nameField.setText("");
                mobileField.setText("");
                passwordField.setText("");
                confirmPwdField.setText("");
                districtField.setText("");
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
