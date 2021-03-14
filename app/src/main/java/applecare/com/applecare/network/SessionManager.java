package applecare.com.applecare.network;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;

import applecare.com.applecare.Model.User;

public class SessionManager {
    private SharedPrepManager sharedPrepManager ;
    private Context mContext;
  private  static SessionManager sessionManager;

    private SessionManager(Context mContext) {
        this.mContext = mContext;
        sharedPrepManager= SharedPrepManager.getInstance(mContext);
    }
    public static SessionManager getSessionManager(Context mContext){
        if(sessionManager == null){
            sessionManager = new SessionManager(mContext);
        }
        return sessionManager;
    }
   /* public String getAuthToken(){
      return   sharedPrepManager.getConfig().getAccessToken();
    }*/
    public void saveConfigData(User user){
         sharedPrepManager.saveUser(user);
    }
    public User getUser(){
       return sharedPrepManager.getUser();
    }
    public String getAuthTokenForSignUP(){
       // return mContext.getString(R.string.authToken);
        return "r<7~=Yl|?ALlVhzMaBf5;)~batejdN";
    }
    public String getAccessToken(){
        // return mContext.getString(R.string.authToken);
        Log.d("TAG", "getAccessToken: "+ getUser().getAccessToken());
        return  getUser().getAccessToken();
    }
}
