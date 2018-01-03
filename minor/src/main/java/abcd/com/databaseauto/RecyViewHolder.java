package abcd.com.databaseauto;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HP on 04-10-2017.
 */

public class RecyViewHolder extends RecyclerView.ViewHolder{
    TextView t;
    EditText e;
    Activity context;
    //  HashMap map=new HashMap();
    public RecyViewHolder(View itemView, Activity c, final EditText e/*,HashMap ma*/) {
        super(itemView);
        t=(TextView)itemView.findViewById(R.id.id1);
       /* e=(EditText)itemView.findViewById(R.id.et);*/
        this.context=c;
        //    this.map=ma;
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //    SharedPreferences sp = MyApplication.getAppContext().getSharedPreferences("main", Context.MODE_PRIVATE);
                //      SharedPreferences.Editor editor = sp.edit();
                //        editor.putString("list",t.getText().toString());
                //          editor.putString("list_id",(String)map.get(t.getText().toString()));
                //  editor.commit();
                Log.d("codekamp",""+t.getText().toString());
                e.setText(""+t.getText().toString());
                //  Log.d("codekamp",sp.getString("list_id",null));
                //Intent intent=new Intent(context,Review.class);
                //context.startActivity(intent);
            }
        });
    }
}

