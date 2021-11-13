package applecare.com.applecare.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import applecare.com.applecare.Model.User;

public class SharedPrepManager {

    private static final String PREF_NAME = "com.example.app.TAKE_AWAY";
    private static final String CONFIG_KEY_VALUE = "com.example.app.USER_CONF";
    private static final String RESTAURANT_KEY_VALUE = "com.example.app.RESTAURANT";

    private static SharedPrepManager sInstance;
    private final SharedPreferences mPref;

    public SharedPrepManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static synchronized SharedPrepManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPrepManager(context);
        }
        return sInstance;
    }

    /*public void saveConfig(LoginResponse loginResponse) {

        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        mPref.edit().putString(CONFIG_KEY_VALUE,json)
                .commit();
    }

    public LoginResponse getConfig() {
        Gson gson = new Gson();
        String json = mPref.getString(CONFIG_KEY_VALUE, "");
        LoginResponse savedConfig = gson.fromJson(json, LoginResponse.class);
       return savedConfig;
    }*/

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }


    public void saveUser(User user) {
        if(user==null){
            mPref.edit().putString(CONFIG_KEY_VALUE,null)
                    .commit();


        }else {
            Gson gson = new Gson();
            String json = gson.toJson(user);
            mPref.edit().putString(CONFIG_KEY_VALUE,json)
                    .commit();

        }


    }
    public User getUser() {
        Gson gson = new Gson();
        String json = mPref.getString(CONFIG_KEY_VALUE, "");
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public void saveMobileNumber(String mobileNumber)
    {
        mPref.edit().putString("mobileNumber",mobileNumber)
                .commit();

    }

    public String getSavedMobileNumber()
    {
        return mPref.getString("mobileNumber",null);
    }
}