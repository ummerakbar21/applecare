package applecare.com.applecare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import applecare.com.applecare.Activity.DetailActivity;
import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;

/**
 * Created by shabir on 03-03-2018.
 */

public class FAQRecyclerViewAdapter extends RecyclerView.Adapter<FAQRecyclerViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<FAQItem> data;
    public FAQRecyclerViewAdapter(Context context, List<FAQItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public FAQRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_faq, parent, false);
        FAQRecyclerViewHolder holder = new FAQRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FAQRecyclerViewHolder holder, int position) {
        final FAQItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivityIntent=new Intent(view.getContext(), DetailActivity.class);
                detailActivityIntent.putExtra("title",current.getTitle());
                view.getContext().startActivity(detailActivityIntent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
