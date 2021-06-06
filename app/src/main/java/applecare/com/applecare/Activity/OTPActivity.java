package applecare.com.applecare.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import applecare.com.applecare.R;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;


public class OTPActivity extends AppCompatActivity {
    private OtpView otpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_o_t_p);
        // calling the action bar
       // ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
      //  actionBar.setDisplayHomeAsUpEnabled(true);
        otpView = findViewById(R.id.otp_view);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

   protected void setOtpViewListener(){
       otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
           @Override public void onOtpCompleted(String otp) {

               // do Stuff
               Log.d("onOtpCompleted=>", otp);
               Toast.makeText(OTPActivity.this, otp, Toast.LENGTH_SHORT).show();
           }
       });
   }
}