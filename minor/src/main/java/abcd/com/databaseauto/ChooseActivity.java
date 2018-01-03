package abcd.com.databaseauto;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private Button b2;
    private Button b3;
    private SharedPreferences my_pref;
    private String ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                //go to main
                PackageManager pm  = ChooseActivity.this.getPackageManager();
                ComponentName componentName = new ComponentName(ChooseActivity.this, SMSBReceiver.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())

        {
            case R.id.button1:
                Intent i1=new Intent(this,CurrentLocation.class);
                startActivity(i1);
                break;
            case R.id.button2:
                my_pref = getSharedPreferences("my_pref",MODE_PRIVATE);
                ques = my_pref.getString("ques", "");
                if(ques.equalsIgnoreCase("")) {
                   Intent i2 = new Intent(this, AddActivity.class);
                    startActivity(i2);
                }
                else
                {
                    Intent i2 = new Intent(this, LostPhone.class);
                    startActivity(i2);
                }
                break;
            case R.id.button3:
                Intent i3=new Intent(this,MainActivity.class);
                startActivity(i3);
                break;

        }
    }

}
