package kudwi.schoolex;

/**
 * Created by harishananth on 08/07/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by harishananth on 21/06/17.
 */

/**
 * Created by harishananth on 20/12/16.
 */

public class snrecyadapter extends RecyclerView.Adapter<snView_Holder> {

    List<sndata> list = Collections.emptyList();
    Context context;

    public snrecyadapter(List<sndata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public snView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolnamecard, parent, false);
        snView_Holder holder = new snView_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final snView_Holder holder, final int position) {

        final sndata item=list.get(position);

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.schoolname.setText(list.get(position).name);
        //holder.description.setText(list.get(p



    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, sndata sndata) {
        list.add(position, sndata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(sndata sndata) {
        int position = list.indexOf(sndata);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
