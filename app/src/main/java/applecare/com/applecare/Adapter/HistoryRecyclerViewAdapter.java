package applecare.com.applecare.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import applecare.com.applecare.Activity.HistoryDetailActivity;
import applecare.com.applecare.Fragment.Question;
import applecare.com.applecare.R;

/**
 * Created by shabir on 03-03-2018.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    List<Question> data;
    public HistoryRecyclerViewAdapter(Context context, List<Question> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
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
        final Question current = data.get(position);

        holder.dateView.setText(current.getAddedOn());
        if(current.isAnswered()){
            holder.statusView.setText("");
        }else  {
            holder.statusView.setText("Yet to answer");
        }

        Picasso.get().load(current.getThumbnail()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current.isAnswered()){
                    Intent detailHistoryIntent=new Intent(view.getContext(), HistoryDetailActivity.class);
                    detailHistoryIntent.putExtra("title",current.getTitle());
                    detailHistoryIntent.putExtra("item",current);
                    view.getContext().startActivity(detailHistoryIntent);
                }else {
                  createAlert();
                }

            }
        });
    }

    private void createAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                mContext);
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage("Your question is not answered yet, please recheck after some time. ");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                      //  caputuredImageView.setImageDrawable(getResources().getDrawable(R.drawable.apple,null));

                        //  Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
