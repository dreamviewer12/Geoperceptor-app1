package abcd.com.databaseauto;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity implements ValueEventListener {

    private DatabaseReference db;
    private EditText etques;

    private SharedPreferences my_pref;
    private String ques1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etques=(EditText)findViewById(R.id.editques);
        my_pref=getSharedPreferences("my_pref",MODE_PRIVATE);
        ques1 = my_pref.getString("ques", "");
        Log.i("codekamp",ques1);
        etques.setText("");

        FirebaseDatabase fd = FirebaseDatabase.getInstance();//connection object

        db = fd.getReference("Secret");//where to store data..

        db.addValueEventListener(this);

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques= etques.getText().toString().trim();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (ques.isEmpty()) {
                        return;
                    }
                }
                SharedPreferences.Editor editor=my_pref.edit();
                editor.putString("ques",ques);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    editor.apply();
                }

                addToFirebase(ques);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }
    private void addToFirebase(String ques) {
        //FirebaseDatabase

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();//gives logged in user
        String email = currentUser.getEmail();
        HashMap<String, Object> map = new HashMap<>();
        map.put("ques", ques);

        map.put("email", email);

        db.push().setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                etques.setText(null);


                if (databaseError == null) {
                    Toast.makeText(AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        if(databaseError!=null)
        {
            Toast.makeText(this,"there was an error" + databaseError.getMessage(),Toast.LENGTH_SHORT).show();      }

    }
}
