package abcd.com.databaseauto;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 04-10-2017.
 */

public class GooglePlayServiceBuilder {
    public static GooglePlayService build() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // SharedPreferences sp=(MyApplication.getAppContext()).getSharedPreferences("main", Context.MODE_PRIVATE);
        // Log.d("codekamp","bye");

        Interceptor myInterceptor = new Interceptor() {
            /* SharedPreferences sp = (MyApplication.getAppContext()).getSharedPreferences("main", Context.MODE_PRIVATE);
             public String API_KEY=sp.getString("api_key",null);*/
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "bearer " +
                        "AIzaSyA7eP7mp-vJiKmUry8sUUuQgDbBCm69-n8").build();//key - authorization value api key If more than 1 header then 2 add header
                //.header will override key and .addheader will add new header and don't override
             /*  Request newRequest = chain.request().newBuilder().addHeader("Authorization", "bearer " +
                       API_KEY).build();*/
                return chain.proceed(newRequest);
            }
        };
        builder.interceptors().add(myInterceptor);
        builder.interceptors().add(logging);
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(GooglePlayService.class);
    }
    private static String getBaseUrl() {
        return "https://maps.googleapis.com/maps/api/place/";
        //return "https://us11.api.mailchimp.com/3.0/";
    }

}
