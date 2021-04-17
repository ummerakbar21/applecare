package applecare.com.applecare.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("username")
    private String userName;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String password;
    private String district;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("firebase_token")
    private String firebaseToken;
    @SerializedName("access")
    private String accessToken;

    public User() {
    }


    public User(String email, String firstName, String lastName, String password, String district, String userType, String firebaseUID) {


        this.userName = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.district = district;
        this.userType = userType;
        this.firebaseToken = firebaseUID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
