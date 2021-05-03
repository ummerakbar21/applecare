package applecare.com.applecare.Activity;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import applecare.com.applecare.Fragment.Question;
import applecare.com.applecare.Model.Answer;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryDetailActivity extends AppCompatActivity {
    private Question selectedItem;
    private  HtmlTextView symptomTextView;
    private  HtmlTextView diseaseTextView;
    private HtmlTextView recomTextView;
    SpotsDialog waitingDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView) findViewById(R.id.htab_header);
        symptomTextView=(HtmlTextView)findViewById(R.id.symptom_text_view);
        diseaseTextView=(HtmlTextView)findViewById(R.id.disease_text_view);
        recomTextView=(HtmlTextView)findViewById(R.id.recommendation_text_view);
        selectedItem = (Question) getIntent().getSerializableExtra("item");
        setTitle(selectedItem.getTitle());
        Picasso.get().load(selectedItem.getThumbnail()).into(imageView);
          fetchData();
        //imageView.setImageDrawable(Utilities.getImageFromDrawable(this,selectedItem.getDrawable()));
//        try {
//           // String dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"symptoms"));
//            symptomTextView.setHtml("Sympotoms goes here");
//           // dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"recommendation"));
//            recomTextView.setHtml("recommendations goes here");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
    private  void fetchData(){
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Please wait...").build();


        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        SessionManager sessionManager = SessionManager.getSessionManager(this);

          apiInterface.getAnswer(" Bearer "+sessionManager.getAccessToken(), selectedItem.getId()).enqueue(new Callback<List<Answer>>() {
              @Override
              public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                    waitingDialog.dismiss();
                  try {
                      Answer answer =  response.body().get(0);
                      diseaseTextView.setHtml(answer.getDiseaseName());
                      // String dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"symptoms"));
                      symptomTextView.setHtml(answer.getSymptoms());

                      // dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"recommendation"));
                      recomTextView.setHtml(answer.getRecommendation());

                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  //createAlert();
              }

              @Override
              public void onFailure(Call<List<Answer>> call, Throwable t) {
                  waitingDialog.dismiss();
              }
          });



    }
}
