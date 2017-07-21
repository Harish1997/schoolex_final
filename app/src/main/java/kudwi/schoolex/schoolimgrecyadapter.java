package kudwi.schoolex;

/**
 * Created by harishananth on 28/06/17.
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
 * Created by harishananth on 25/06/17.
 */

public class schoolimgrecyadapter extends RecyclerView.Adapter<schoolimgView_Holder> {

    List<Imgdata> list= Collections.emptyList();
    Context context;

    schoolimgrecyadapter(List<Imgdata> list,Context context)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public schoolimgView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolimagecard, parent, false);
        schoolimgView_Holder holder = new schoolimgView_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(schoolimgView_Holder holder, int position) {

        final Imgdata item=list.get(position);

        holder.imageView.setImageResource(list.get(position).Imageid);
        Glide.with(context).load(list.get(position).url).into(holder.imageView);
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
    public void insert(int position, Imgdata spldata) {
        list.add(position, spldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Imgdata spldata) {
        int position = list.indexOf(spldata);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
