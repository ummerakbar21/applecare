package applecare.com.applecare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import applecare.com.applecare.Activity.DetailActivity;
import applecare.com.applecare.Model.HistoryItem;
import applecare.com.applecare.R;

/**
 * Created by shabir on 03-03-2018.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<HistoryItem> data;
    public HistoryRecyclerViewAdapter(Context context, List<HistoryItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public HistoryRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_history, parent, false);
        HistoryRecyclerViewHolder holder = new HistoryRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryRecyclerViewHolder holder, int position) {
        final HistoryItem current = data.get(position);
        holder.title.setText(current.getTitle());
        // temporary disable
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivityIntent=new Intent(view.getContext(), DetailActivity.class);
                detailActivityIntent.putExtra("title",current.getTitle());
                view.getContext().startActivity(detailActivityIntent);
            }
        });*/
    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
