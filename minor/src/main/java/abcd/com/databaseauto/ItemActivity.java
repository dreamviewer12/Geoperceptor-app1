package abcd.com.databaseauto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by HP on 04-10-2017.
 */

public class ItemActivity extends AppCompatActivity {
    EditText e;
    String p,key="pla";
    RecyclerView rv;
    DatabaseHelper helper=new DatabaseHelper();
    TodoAdapter adapter ;
    //constructor of recyadapter call where from database items go to list array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent i =getIntent();
        p=i.getStringExtra(key);
        adapter=new TodoAdapter(p);
      /* adapter.pl=p;*/

        e = (EditText) findViewById(R.id.et);
        rv = (RecyclerView) findViewById(R.id.rev);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
    public void todoadd(View v)
    {
        String msg=e.getText().toString();
        e.setText(" ");
        // Log.d("codekamp",""+p);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("item",msg);
        contentValues.put("place",p);
        int id =(int)db.insert("todo",null,contentValues);
        db.close();
        Todo t= new Todo(id,p,msg);
        //TodoAdapter adapter=new TodoAdapter(p);
        adapter.lists.add(t);
        //  TodoAdapter ad = new TodoAdapter(p);
        adapter.pl = p;
        Log.d("codekamp","ItemActivity "+p);
        adapter.notifyDataSetChanged();
    }
}
