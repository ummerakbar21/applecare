package applecare.com.applecare.Utils;

import java.io.IOException;
import java.lang.annotation.Annotation;

import applecare.com.applecare.Model.ErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import static applecare.com.applecare.network.APIClient.getClient;

public class ErrorUtils {
    public static ErrorResponse parseError(Response<?> response) {
        Converter<ResponseBody, ErrorResponse> converter =
                getClient()
                        .responseBodyConverter(ErrorResponse.class, new Annotation[0]);

        ErrorResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponse();
        }

        return error;
    }
}
