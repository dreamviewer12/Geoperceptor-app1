package abcd.com.databaseauto;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NotificationActivity extends AppCompatActivity {

    List<String> list = new ArrayList<>();
    TextToSpeech t1;
    String p,key="place";
    RecyclerView rv;
    DatabaseHelper helper=new DatabaseHelper();
    TodoAdapter adapter ;
    Button b;
    //constructor of recyadapter call where from database items go to list array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
      b=(Button)findViewById(R.id.btn);
        Intent i =getIntent();
        p=i.getStringExtra(key);
        Log.d("codekamp","In notification"+p);
        adapter=new TodoAdapter(p);
        this.list=adapter.list;
      adapter.pl=p;

       // e = (EditText) findViewById(R.id.et);
        rv = (RecyclerView) findViewById(R.id.rev);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
                Log.d("codekamp","After some time");
                t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            Log.d("codekamp","No error");
                            t1.setLanguage(Locale.US);
                            speakOut();
                        }
                    }
                });
                //b.setOnClickListener(new View.OnClickListener() {
                  //  @Override
                   // public void onClick(View v) {
                   // }
                //});
            }
            public void speakOut()
            {
                Log.d("codekamp","Speak Out function is called");
                t1.speak("You have to do following tasks",TextToSpeech.QUEUE_ADD,null);
                for(String temp :list) {

                    Log.d("codekamp",""+temp);
                    t1.speak(temp, TextToSpeech.QUEUE_ADD, null);
                    t1.playSilence(2000,TextToSpeech.QUEUE_ADD,null);
                }
            }
    }

