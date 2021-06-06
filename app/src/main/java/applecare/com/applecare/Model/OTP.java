package applecare.com.applecare.Model;

import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.gson.annotations.SerializedName;

public class OTP {
    @SerializedName("Status")
    private String status;
    @SerializedName("Details")
    private String details;

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
