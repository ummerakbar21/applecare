package applecare.com.applecare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import applecare.com.applecare.Activity.DetailActivity;
import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Utilities;

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
    public void onBindViewHolder(FAQRecyclerViewHolder holder, final int position) {
        FAQItem item = data.get(position);
        holder.title.setText(item.getDiseaseName());
        holder.faqImageView.setImageDrawable(Utilities.getImageFromDrawable(context,item.getDrawable()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivityIntent=new Intent(view.getContext(), DetailActivity.class);
                detailActivityIntent.putExtra("pos",position);
                detailActivityIntent.putExtra("item",data.get(position));
                view.getContext().startActivity(detailActivityIntent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
