package abcd.com.databaseauto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nitisha Agarwal on 10-10-2017.
 */

public  interface GeoService {
    @GET("json")
   Call<LangLongResponse> getAllLists(@Query("address") String address, @Query("key") String key);
}
