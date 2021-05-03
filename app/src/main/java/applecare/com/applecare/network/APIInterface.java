package applecare.com.applecare.network;


import java.util.List;

import applecare.com.applecare.Fragment.Question;
import applecare.com.applecare.Model.Answer;
import applecare.com.applecare.Model.LoginUser;
import applecare.com.applecare.Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("users/")
    Call<User> userSignLogin(@Body User request, @Header("TOKEN") String authorization);
    @POST("login/")
    Call<User> loginUser(@Body LoginUser request, @Header("TOKEN") String authorization);
    @Multipart
    @POST("questions/")
    Call<Question> uploadQuestion(@Header("Authorization") String authorization ,@Part("description") RequestBody description,  @Part MultipartBody.Part photo);

    @GET("questions/")
    Call<List<Question>> getQuestions(@Header("Authorization") String authorization );
    @GET("questions/")
    Call<List<Question>> getQuestions(@Header("Authorization") String authorization, @Query("filter") String filter);

    @POST("questions/{id}/answers/")
    Call<Answer> postAnswer(@Header("Authorization") String authorization, @Body Answer request,  @Path("id") int id);
    @GET("questions/{id}/answers/")
    Call<List<Answer>> getAnswer(@Header("Authorization") String authorization,  @Path("id") int id);



}
