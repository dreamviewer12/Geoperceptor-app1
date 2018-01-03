package abcd.com.databaseauto;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WhennoActivity extends AppCompatActivity {

    TextView t;
    private SharedPreferences my_pref;
    private String ques1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whenno);
        t = (TextView)findViewById(R.id.sec);
        my_pref=getSharedPreferences("my_pref",MODE_PRIVATE);
        ques1 = my_pref.getString("ques", "");
        Log.i("codekamp",ques1);
        t.setText(ques1);
    }
}
