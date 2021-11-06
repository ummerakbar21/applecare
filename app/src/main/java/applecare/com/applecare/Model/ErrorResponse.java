package applecare.com.applecare.Model;

public class ErrorResponse {
    private String Error;

    public ErrorResponse(String Error) {
        this.Error = Error;
    }
    public ErrorResponse() {
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
