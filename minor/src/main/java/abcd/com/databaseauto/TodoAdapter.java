package abcd.com.databaseauto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 04-10-2017.
 */

public class TodoAdapter  extends RecyclerView.Adapter<TodoViewHolder> {
    List<Todo> lists= new ArrayList<>();
    List<String> list= new ArrayList<>();
    DatabaseHelper helper;
    String pl;
    /*public TodoAdapter(String p)
    {
        pl=p;
    }*/
    public TodoAdapter(String plac)
    {
        pl = plac;
        list.clear();
        helper = new DatabaseHelper();
        SQLiteDatabase db = helper.getReadableDatabase();
        //String[] s = new String[] {pl};
        Log.d("codekamp","hi hello "+ pl);
        String[] s = new String[] {pl};
        String query = " select * from '" + helper.todo_table + " 'where place = '" + pl +"'";
        // Cursor c = db.query(helper.todo_table,null,"_id = 1 ",null,null,null,null);
        Cursor c = db.query(helper.todo_table,null," place =?" ,s,null,null,null);
        TodoCursor cw= new TodoCursor(c);
        while(cw.moveToNext())
        {
            lists.add(cw.getTodo());
            int id = cw.getInt(cw.getColumnIndex("_id"));
            String item = cw.getString(cw.getColumnIndex("item"));
            String place = cw.getString(cw.getColumnIndex("place"));
            list.add(item);
            Log.d("codekamp","hi "+id+" "+item+" "+place);
            //cw which has called extract all the items and give it to list
            //Log.d("codekamp","Constructor adapter"+lists);
        }

    }



    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.itemrecycler_layout,parent,false);
        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position)
    {
        Todo t = lists.get(position);
        //holder.v.setText(t.title);

        holder.setTodo(t);
        //  for(int i = 0; i < ar.size(); i++) {
        //    holder.v.setText(ar.get(i));
        //}
        Log.d("codekamp","OnBindViewHolder"+lists.size());
    }

    @Override
    public int getItemCount()
    {
        return lists.size();
    }
}

