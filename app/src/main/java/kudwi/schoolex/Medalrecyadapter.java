package kudwi.schoolex;

/**
 * Created by harishananth on 21/06/17.
 */

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by harishananth on 20/12/16.
 */

public class Medalrecyadapter extends RecyclerView.Adapter<Medalview_holder> {

    List<Medaldata> list = Collections.emptyList();
    Context context;

    public Medalrecyadapter(List<Medaldata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Medalview_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medalcard, parent, false);
        Medalview_holder holder = new Medalview_holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final Medalview_holder holder, final int position) {

        final Medaldata item=list.get(position);

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.medaltitle.setText(list.get(position).title);
        //holder.description.setText(list.get(p
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.subtitle.setText(list.get(position).subtitle);


        //animate(holder);

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
    public void insert(int position, Medaldata medaldata) {
        list.add(position, medaldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Medaldata medaldata) {
        int position = list.indexOf(medaldata);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
