package kudwi.schoolex;

/**
 * Created by harishananth on 21/06/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;


public class toprecyadapter extends RecyclerView.Adapter<topView_Holder> {

    List<topdata> list = Collections.emptyList();
    Context context;

    public toprecyadapter(List<topdata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public topView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topschoolcard, parent, false);
        topView_Holder holder = new topView_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final topView_Holder holder, final int position) {

        final topdata item=list.get(position);

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        //holder.description.setText(list.get(p

        holder.imageView.setImageResource(list.get(position).imageId);
        Glide.with(context).load(list.get(position).url).into(holder.imageView);
        holder.ratingBar.setRating(list.get(position).rval);


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
    public void insert(int position, topdata spldata) {
        list.add(position, spldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(topdata spldata) {
        int position = list.indexOf(spldata);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
