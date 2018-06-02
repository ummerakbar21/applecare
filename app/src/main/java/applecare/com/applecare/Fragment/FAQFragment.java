package applecare.com.applecare.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import applecare.com.applecare.Adapter.FAQRecyclerViewAdapter;
import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;

/**
 * Created by shabir on 03-03-2018.
 */

public class FAQFragment extends Fragment {
    public static List<FAQItem> faqItems;
    private RecyclerView faqRecyclerView;
    private FAQRecyclerViewAdapter faqRecyclerViewAdapter;
    public FAQFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_faq,container,false);
        faqRecyclerView=(RecyclerView)view.findViewById(R.id.faq_recycler_view);
        //adding list of items to view
        addItemToView(view);
        return view;
    }

    private List<FAQItem> getFaqList() {

         faqItems = new ArrayList<FAQItem>();
        faqItems.add(new FAQItem(1,"Scab","Throughout the season",this.getResources().getDrawable(R.drawable.scab)));
        faqItems.add(new FAQItem(2,"Alternaria leaf spot","Late spring and early summer",this.getResources().getDrawable(R.drawable.alternaria_leaf_spot)));
        faqItems.add(new FAQItem(3,"Marsonina leaf blotch","Summer",this.getResources().getDrawable(R.drawable.marsonian)));
        faqItems.add(new FAQItem(4,"Powdery mildew","When buds develop into new leaves and shoots",this.getResources().getDrawable(R.drawable.powdrew_mildew)));
        faqItems.add(new FAQItem(5,"Sooty blotch and Flyspeck","Late spring",this.getResources().getDrawable(R.drawable.apples_flyspeck_sooty_blotch)));
        faqItems.add(new FAQItem(6,"Black rot","Early spring",this.getResources().getDrawable(R.drawable.black_rot)));
        faqItems.add(new FAQItem(7,"Core rot","Spring",this.getResources().getDrawable(R.drawable.core_rot)));
        faqItems.add(new FAQItem(8,"Collar Rot","Any time",this.getResources().getDrawable(R.drawable.collar_rot)));


        return faqItems;
    }


    private void addItemToView(View view) {
        faqRecyclerViewAdapter=new FAQRecyclerViewAdapter(view.getContext(),getFaqList());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),2);
        faqRecyclerView.setLayoutManager(mLayoutManager);
        faqRecyclerView.setAdapter(faqRecyclerViewAdapter);
        faqRecyclerViewAdapter.notifyDataSetChanged();
    }
}
