package applecare.com.applecare.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import applecare.com.applecare.Adapter.FragmentPagerAdapter;
import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;

import static applecare.com.applecare.Fragment.FAQFragment.faqItems;

public class DetailActivity extends AppCompatActivity {
    FragmentPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView) findViewById(R.id.htab_header);
        int position=getIntent().getIntExtra("pos",0);
        setTitle(faqItems.get(position).getDiseaseName());
        imageView.setImageDrawable(faqItems.get(position).getDrawable());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPagerAdapter =
                new FragmentPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
