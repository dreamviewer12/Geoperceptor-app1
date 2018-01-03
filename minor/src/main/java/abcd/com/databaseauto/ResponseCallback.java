package abcd.com.databaseauto;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04-10-2017.
 */

abstract public class ResponseCallback<T> implements Callback<T> {
    //An HTTP response may still indicate an application-level failure such as a 404 or 500.
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        /** Returns true if {@link #code()} is in the range [200..300). */
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            Gson gson = new Gson();
            try {
                ApiError error = gson.fromJson(response.errorBody().string(), ApiError.class);

                onError(error);
            } catch (IOException e) {
            }
        }
    }
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(new ApiError(t.getMessage(),"ERROR" ));
    }
    abstract public void onSuccess(T response);
    abstract public void onError(ApiError error);
}

