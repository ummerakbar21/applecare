package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import applecare.com.applecare.Model.SignUpUser;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Constants;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout rootLayout;
    private TextInputEditText emailField;
    private TextInputEditText nameField;
    private TextInputEditText passwordField;
    private TextInputEditText confirmPwdField;
    private TextInputEditText districtField;
    private Button signUpBtn;
    private TextView alreadySignUpLink;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String district;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        initializeView();

    }

    private void initializeView() {

        rootLayout = findViewById(R.id.register_root);
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
            case R.id.signup_join_button:
                signUpUser();
                break;
            default:
                break;
        }

    }

    private void signUpUser() {
        getTextFromEditTexts();

        if (TextUtils.isEmpty(email)){
            emailField.setError("Email is required");
        }

       else if (TextUtils.isEmpty(name)){
            nameField.setError("Name is Required");

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
        else {

            registerUser();
        }

    }

    private void getTextFromEditTexts() {
        name = nameField.getText().toString();
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
        confirmPassword = confirmPwdField.getText().toString();
        district = districtField.getText().toString();
    }

    private void registerUser() {
    //    waitingDialog.show();
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
                 //   waitingDialog.dismiss();
                    Snackbar.make(rootLayout,"This User is already registered",Snackbar.LENGTH_LONG).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            SignUpUser signUpUser = new SignUpUser(email,name,password,district);
                            databaseReference.child(mAuth.getUid()).setValue(signUpUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                  //  waitingDialog.dismiss();
                                //    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                             //       startActivity(intent);
                                 //   finish();
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


    }


}
