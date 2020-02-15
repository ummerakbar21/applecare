package applecare.com.applecare.Adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import applecare.com.applecare.Activity.DetailActivity;
import applecare.com.applecare.Activity.HistoryDetailActivity;
import applecare.com.applecare.Model.HistoryItem;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Utilities;

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
        holder.imageView.setImageDrawable(Utilities.getImageFromDrawable(context,current.getDrawable()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailHistoryIntent=new Intent(view.getContext(), HistoryDetailActivity.class);
                detailHistoryIntent.putExtra("title",current.getTitle());
                detailHistoryIntent.putExtra("item",current);
                view.getContext().startActivity(detailHistoryIntent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
