package trainage.minor_sem_5;

import android.content.Intent;
import android.os.Bundle;
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                //go to main
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
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
                Intent i2=new Intent(this,AddActivity.class);
                startActivity(i2);
                break;
            case R.id.button3:
                Intent i3=new Intent(this,Reminder.class);
                startActivity(i3);
                break;

        }
    }
}
