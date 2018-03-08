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

        List<FAQItem> allItems = new ArrayList<FAQItem>();
        allItems.add(new FAQItem("United States"));
        allItems.add(new FAQItem("Canada"));
        allItems.add(new FAQItem("United Kingdom"));
        allItems.add(new FAQItem("Germany"));
        allItems.add(new FAQItem("Sweden"));
        allItems.add(new FAQItem("United Kingdom"));
        allItems.add(new FAQItem("Germany"));
        allItems.add(new FAQItem("Sweden"));
        allItems.add(new FAQItem("United States"));
        allItems.add(new FAQItem("Canada"));
        allItems.add(new FAQItem("United Kingdom"));
        allItems.add(new FAQItem("Germany"));
        allItems.add(new FAQItem("Sweden"));
        allItems.add(new FAQItem("United Kingdom"));
        allItems.add(new FAQItem("Germany"));
        allItems.add(new FAQItem("Sweden"));

        return allItems;
    }


    private void addItemToView(View view) {
        faqRecyclerViewAdapter=new FAQRecyclerViewAdapter(view.getContext(),getFaqList());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),2);
        faqRecyclerView.setLayoutManager(mLayoutManager);
        faqRecyclerView.setAdapter(faqRecyclerViewAdapter);
        faqRecyclerViewAdapter.notifyDataSetChanged();
    }
}
