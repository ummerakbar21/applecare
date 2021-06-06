package applecare.com.applecare.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import applecare.com.applecare.Adapter.HistoryRecyclerViewAdapter;
import applecare.com.applecare.Adapter.QuestionExpertRecyclerViewAdapter;
import applecare.com.applecare.R;
import applecare.com.applecare.network.APIClient;
import applecare.com.applecare.network.APIInterface;
import applecare.com.applecare.network.SessionManager;
import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by shabir on 03-03-2018.
 */

public class QuestionFragmentExpert extends Fragment {
    private RecyclerView historyRecyclerView;
    private QuestionExpertRecyclerViewAdapter historyAdapter;
    private TextView noQuestion;
    SpotsDialog waitingDialog ;
    public QuestionFragmentExpert(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history,container,false);
        historyRecyclerView=(RecyclerView)view.findViewById(R.id.history_recycler_view);
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(getContext()).setMessage("Please wait...").build();

        noQuestion= view.findViewById(R.id.no_question);
        //adding list of items to view
        addItemToView(view);
        getFaqList();
        return view;
    }

    private  void  getFaqList() {
        waitingDialog.show();
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface=retrofit.create(APIInterface.class);
        SessionManager sessionManager = SessionManager.getSessionManager(getActivity());
          apiInterface.getQuestions(" Bearer "+sessionManager.getAccessToken(), "unanswered").enqueue(new Callback<ResponseBody>() {
              @Override
              public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                  waitingDialog.dismiss();
                  if(response.body()==null ){
                      noQuestion.setVisibility(View.VISIBLE);

                  }else {
                      List<Question> questions = new ArrayList<>();
                      try {
                          JSONObject jsonObject = new JSONObject(response.body().string());
                          Gson gson = new Gson();
                          questions= gson.fromJson(String.valueOf(jsonObject.optJSONArray("results")), new TypeToken<List<Question>>(){}.getType());
                      } catch (JSONException | IOException e) {
                          e.printStackTrace();
                      }
                      historyAdapter=new QuestionExpertRecyclerViewAdapter(getContext(),questions);
                      historyRecyclerView.setAdapter(historyAdapter);
                      historyAdapter.notifyDataSetChanged();
                      noQuestion.setVisibility(View.GONE);
                  }


              }

              @Override
              public void onFailure(Call<ResponseBody> call, Throwable t) {
                  noQuestion.setVisibility(View.VISIBLE);
                  noQuestion.setText("Something went wrong please check later");
                  waitingDialog.dismiss();

              }
          });

       // List<QuestionHistory> allItems = new ArrayList<QuestionHistory>();
        //

        //return allItems;




        //return null;
    }

    private void addItemToView(View view) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        historyRecyclerView.setLayoutManager(mLayoutManager);

    }
}
