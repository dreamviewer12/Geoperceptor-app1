package abcd.com.databaseauto;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitisha Agarwal on 10-10-2017.
 */

public class GeoServiceBuilder {
    public static GeoService build() {
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
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "bearer " + "AIzaSyDHCINWFgYuLLekxDh5RzrCczculdOoRwk").build();
                // SharedPrefs.getApiKey()).build();//key - authorization value api key If more than 1 header then 2 add header
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
        return retrofit.create(GeoService.class);
    }
    private static String getBaseUrl() {
       /* String API_KEY=SharedPrefs.getApiKey();
        Log.d("codekamp","bye"+API_KEY);*/
       /* int l=API_KEY.length();
        Log.d("codekamp",""+l);
        String s=""+API_KEY.charAt(l-4)+API_KEY.charAt(l-3)+API_KEY.charAt(l-2)+API_KEY.charAt(l-1);
        String MAILCHIMP_BASE_URL="https://"+s+".api.mailchimp.com/3.0/";
        Log.d("codekamp",""+MAILCHIMP_BASE_URL);
        return MAILCHIMP_BASE_URL;*/
        return "https://maps.googleapis.com/maps/api/geocode/";
        //return "https://us11.api.mailchimp.com/3.0/";
    }
}
