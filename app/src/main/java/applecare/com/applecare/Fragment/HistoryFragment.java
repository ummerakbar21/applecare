package applecare.com.applecare.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import applecare.com.applecare.Adapter.FAQRecyclerViewAdapter;
import applecare.com.applecare.Adapter.HistoryRecyclerViewAdapter;
import applecare.com.applecare.Model.HistoryItem;
import applecare.com.applecare.R;

/**
 * Created by shabir on 03-03-2018.
 */

public class HistoryFragment extends Fragment {
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerViewAdapter historyAdapter;
    public HistoryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history,container,false);
        historyRecyclerView=(RecyclerView)view.findViewById(R.id.history_recycler_view);
        //adding list of items to view
        addItemToView(view);
        return view;
    }

    private List<HistoryItem> getFaqList() {

        List<HistoryItem> allItems = new ArrayList<HistoryItem>();
        allItems.add(new HistoryItem(1,"Scab","Throughout the season","scab", "scab"));


        return allItems;
    }

    private void addItemToView(View view) {
        historyAdapter=new HistoryRecyclerViewAdapter(view.getContext(),getFaqList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        historyRecyclerView.setLayoutManager(mLayoutManager);
        historyRecyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }
}
