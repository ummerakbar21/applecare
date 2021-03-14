package applecare.com.applecare.Activity;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import applecare.com.applecare.Fragment.Question;
import applecare.com.applecare.R;

public class HistoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView) findViewById(R.id.htab_header);
        HtmlTextView symptomTextView=(HtmlTextView)findViewById(R.id.symptom_text_view);
        HtmlTextView recomTextView=(HtmlTextView)findViewById(R.id.recommendation_text_view);
        Question selectedItem = (Question) getIntent().getSerializableExtra("item");
        setTitle(selectedItem.getTitle());
        Picasso.get().load(selectedItem.getThumbnail()).into(imageView);

        //imageView.setImageDrawable(Utilities.getImageFromDrawable(this,selectedItem.getDrawable()));
        try {
           // String dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"symptoms"));
            symptomTextView.setHtml("Sympotoms goes here");
           // dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"recommendation"));
            recomTextView.setHtml("recommendations goes here");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
