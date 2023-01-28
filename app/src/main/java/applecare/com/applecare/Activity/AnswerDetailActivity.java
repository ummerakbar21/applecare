package applecare.com.applecare.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import applecare.com.applecare.Fragment.Question;
import applecare.com.applecare.Model.Answer;
import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Utilities;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnswerDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private  Spinner spinner;
    public ArrayList<FAQItem> faqItems;
    ArrayAdapter<CharSequence> adapter;
    EditText symptomsEV;
    EditText recommendedEV;
    Button submitBtn;
    EditText diseaseName;
    Question selectedItem;
    FAQItem faqItem = new FAQItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView) findViewById(R.id.htab_header);
        HtmlTextView symptomTextView=(HtmlTextView)findViewById(R.id.symptom_text_view);
        HtmlTextView recomTextView=(HtmlTextView)findViewById(R.id.recommendation_text_view);
        submitBtn = findViewById(R.id.submit_button_view);
        submitBtn.setOnClickListener(this);
        selectedItem = (Question) getIntent().getSerializableExtra("item");
        setTitle(selectedItem.getTitle());
        Picasso.get().load(selectedItem.getThumbnail()).into(imageView);
         getFaqList();
        //imageView.setImageDrawable(Utilities.getImageFromDrawable(this,selectedItem.getDrawable()));
        try {
           // String dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"symptoms"));
            symptomTextView.setHtml("Sympotom");
           // dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"recommendation"));
            recomTextView.setHtml("recommendations goes here");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        diseaseName = findViewById(R.id.disease_edit_view);
        symptomsEV = findViewById(R.id.symptom_edit_view);
        recommendedEV = findViewById(R.id.recommendation_edit_view);
        spinner =  findViewById(R.id.planets_spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
         adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if(pos == 0 ){

        }else if (pos==adapter.getCount()){

        }else {

            FAQItem faqItem = faqItems.get(pos-1);
            setData(faqItem);


        }


    }

    private void setData(FAQItem faqItem) {
        diseaseName.setText(faqItem.getDiseaseName());
        symptomsEV.setText(faqItem.getSymptom());
        recommendedEV.setText(faqItem.getRecommendation());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {



    }

    private List<FAQItem> getFaqList() {

        faqItems = new ArrayList<FAQItem>();
        String jsonFileString = Utilities.getJsonFromAssets(getApplicationContext(), "diseases.json");
       // Gson gson = new Gson();
       // Type listDiseaseType = new TypeToken<List<FAQItem>>() { }.getType();
        JSONArray jsonArray = null;
        try {
           jsonArray = new JSONArray(jsonFileString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i =0 ;i< jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            FAQItem faqItem = new FAQItem(jsonObject.optString("name"),jsonObject.optString("symptom"),jsonObject.optString("recommendation"));
           faqItems.add(faqItem);
        }
       return  faqItems;
        // return gson.fromJson(jsonFileString, listDiseaseType);
    }
    private void submitResponse(Answer answer){
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        SessionManager sessionManager = SessionManager.getSessionManager(this);

        apiInterface.postAnswer(" Bearer "+sessionManager.getAccessToken(),answer, selectedItem.getId()).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {

                 createAlert();
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Toast.makeText(AnswerDetailActivity.this, "error is " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }

    @Override
    public void onClick(View view) {
        String diseaseNameText=  diseaseName.getText().toString();
        if(diseaseNameText.isEmpty()){
            diseaseName.setError("Disease name is Required");
            return;
        }
        Answer answer = new Answer();
        answer.setDiseaseName(diseaseNameText);
        answer.setSymptoms(symptomsEV.getText().toString());
        answer.setRecommendation(recommendedEV.getText().toString());
        submitResponse(answer);


    }
    private void createAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
              this);
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage("Your Answer is uploaded.");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                        //caputuredImageView.setImageDrawable(getResources().getDrawable(R.drawable.apple,null));

                        //  Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
}
