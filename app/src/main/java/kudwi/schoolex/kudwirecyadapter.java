package kudwi.schoolex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by harishananth on 25/06/17.
 */

public class kudwirecyadapter extends RecyclerView.Adapter<kudwiView_Holder> {

    List<kudwidata> list= Collections.emptyList();
    Context context;

    kudwirecyadapter(List<kudwidata> list,Context context)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public kudwiView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewcard, parent, false);
        kudwiView_Holder holder = new kudwiView_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(kudwiView_Holder holder, int position) {

        final kudwidata item=list.get(position);
        holder.name.setText(list.get(position).name);
        holder.review.setText(list.get(position).review);
        holder.ratingBar.setRating(list.get(position).rating);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, kudwidata spldata) {
        list.add(position, spldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(kudwidata spldata) {
        int position = list.indexOf(spldata);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
