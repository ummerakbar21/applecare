package applecare.com.applecare.network;

import retrofit2.Call;
import retrofit2.Response;

public interface RetrofitCallback {
    void onResponse(Call<Object> call, Response<Object> response);
    void onFailure(Call<Object> call, Throwable t);
}
