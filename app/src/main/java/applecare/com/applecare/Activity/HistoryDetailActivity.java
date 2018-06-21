package applecare.com.applecare.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.Model.HistoryItem;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Utilities;

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
        HistoryItem selectedItem = (HistoryItem) getIntent().getSerializableExtra("item");
        setTitle(selectedItem.getTitle());
        imageView.setImageDrawable(Utilities.getImageFromDrawable(this,selectedItem.getDrawable()));
        try {
            String dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"symptoms"));
            symptomTextView.setHtml(dataString);
            dataString=getString(Utilities.getStringId(getBaseContext(),selectedItem.getLocalName(),"recommendation"));
            recomTextView.setHtml(dataString);
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
