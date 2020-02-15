package applecare.com.applecare.Adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import applecare.com.applecare.R;


/**
 * Created by Shabir on 03/03/2018.
 */

public class HistoryRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView imageView;
    public HistoryRecyclerViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.imageView);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}
