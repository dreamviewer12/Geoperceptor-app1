package abcd.com.databaseauto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HP on 04-10-2017.
 */

public interface GooglePlayService {
    @GET("autocomplete/json")
    Call<AllPlacesResponse> getAllPlaces(@Query("input") String input, @Query("key") String key);
}

