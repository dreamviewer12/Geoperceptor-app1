package abcd.com.databaseauto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import static android.support.v7.appcompat.R.styleable.View;

public class MainActivity extends AppCompatActivity {
    Context c;
    /*ArrayList<String> title = new ArrayList<>();*/
    String [] tit;
    String key="place";
    String key1="lat";
    String key2="long";
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText e ;
        Button don;
        e = (EditText)findViewById(R.id.et);
        don=(Button)findViewById(R.id.btn);
        final RecyclerView rv;
        rv=(RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        c=this;
        // final AutoCompleteTextView ACTV=(AutoCompleteTextView)findViewById(R.id.actv);
      don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 p= e.getText().toString();
                //  TodoAdapter  ad = new TodoAdapter(p);
                GeoServiceBuilder.build().getAllLists(p,"AIzaSyDHCINWFgYuLLekxDh5RzrCczculdOoRwk").enqueue(new ResponseCallback<LangLongResponse>() {


                    @Override
                    public void onSuccess(LangLongResponse response) {
                        Log.d("codekamp", "onSuccess called");
                       /* Log.d("codekamp", response.lists.get(0).title);
                        recyadapter adapter= new recyadapter(c,response);
                        adapter.co=con;//can not do adapter.co=this because this here would assign this of anonymous class i.e. object of all lists response
                        rv.setAdapter(adapter);
                        p.setVisibility(View.GONE);*/
                        Double la = response.results.get(0).geo.location.lat;
                        Double lo = response.results.get(0).geo.location.longi;
                        Log.d("codekamp","Latitude is "+la+" "+lo);
                       Log.d("codekamp","Longitude is "+lo);
                        Bundle b= new Bundle();
                        Intent i = new Intent(MainActivity.this,GeoMainActivity.class);
                       /* Bundle b = new Bundle();*/
                        b.putDouble(key1,la);
                        i.putExtras(b);
                       /* i.putExtra(key1,la);*/
                       b.putDouble(key2,lo);
                        i.putExtras(b);
                        i.putExtra(key,p);
                        startActivity(i);
                       /* t1.setText(""+la);
                        t2.setText(""+lo);*/

                    }

                    @Override
                    public void onError(ApiError error)
                    {
                        Log.d("codekamp", "onError called");
                        Log.d("codekamp", error.message);
                       /* SharedPrefs.setApiKey(null);
                        p.setVisibility(View.GONE);
                        t.setVisibility(View.VISIBLE);
                        terr.setText("Error "+error.status+"  "+error.message);*/

                    }
                });
                Log.d("codekamp", "onCreate complete");
               /* Intent i =new Intent(MainActivity.this,ItemActivity.class);
                i.putExtra(key,p);
                startActivity(i);*/
            }
        });
        e.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String msg = s.toString();
                GooglePlayServiceBuilder.build().getAllPlaces(msg,"AIzaSyA7eP7mp-vJiKmUry8sUUuQgDbBCm69-n8").enqueue(new ResponseCallback<AllPlacesResponse>() {
                    @Override
                    public void onSuccess(AllPlacesResponse response) {
                        Log.d("codekamp", "onSuccess called");
                        int size = response.predictions.size();
                        // Log.d("codekamp", response.lists.get(0).title);
                        recyadapter adapter= new recyadapter(c,response);
                        adapter.et=e;
                        adapter.s=size;
                        // adapter.co=con;//can not do adapter.co=this because this here would assign this of anonymous class i.e. object of all lists response
                        rv.setAdapter(adapter);
                        //p.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ApiError error)
                    {
                        Log.d("codekamp", "onError called");
                        Log.d("codekamp", error.message);
                        //  SharedPrefs.setApiKey(null);
                        //p.setVisibility(View.GONE);
                        //t.setVisibility(View.VISIBLE);
                        //terr.setText("Error "+error.status+"  "+error.message);

                    }
                });
                Log.d("codekamp", "onCreate complete");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
}