package applecare.com.applecare.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import applecare.com.applecare.R;


/**
 * Created by Shabir on 03/03/2018.
 */

public class FAQRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView faqImageView;
    public FAQRecyclerViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        faqImageView=(ImageView)itemView.findViewById(R.id.faq_image_view);
    }
}
