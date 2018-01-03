package abcd.com.databaseauto;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HP on 04-10-2017.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder
{
    Todo todo;
    TextView v;
    /* ImageView im;
     CheckBox c;*/
    DatabaseHelper helper=new DatabaseHelper();
    //TodoAdapter adapter=new TodoAdapter();
    public TodoViewHolder(View itemView) {
        super(itemView);
        v = (TextView) itemView.findViewById(R.id.tv);
    }

    public void setTodo(Todo t) {
        todo = t;
        v.setText(t.item);
    }
}
