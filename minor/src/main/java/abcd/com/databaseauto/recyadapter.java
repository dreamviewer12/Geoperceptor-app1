package abcd.com.databaseauto;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by HP on 04-10-2017.
 */

public class recyadapter extends RecyclerView.Adapter<RecyViewHolder> {
    Context context;
    AllPlacesResponse re;
    Activity co;
    EditText et;
    int s;
    // HashMap m=new HashMap();

    public recyadapter(Context c, AllPlacesResponse response)
    {
        this.context = c;
        re=response;
    }
    @Override
    public RecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_layout,parent,false);
        return new RecyViewHolder(view,co,et);
    }

    @Override
    public void onBindViewHolder(RecyViewHolder holder, int position)
    {
        //  m.put(re.lists.get(position).title,re.lists.get(position).id);
       /* if(position%2==0)
            holder.t.setBackgroundResource(R.color.green);
        else
            holder.t.setBackgroundResource(R.color.blue);*/
        holder.t.setText("" + re.predictions.get(position).name);
    }

    @Override
    public int getItemCount() {
        return s;
    }
}

