package applecare.com.applecare.Model;

import com.google.gson.annotations.SerializedName;

public class LoginUser {
    @SerializedName("username")
    private String userName;
    private String password;

    @SerializedName("firebase_token")
    private String firebaseToken;
    public LoginUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginUser(String userName, String password, String firebaseToken) {
        this.userName = userName;
        this.password = password;
        this.firebaseToken = firebaseToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
