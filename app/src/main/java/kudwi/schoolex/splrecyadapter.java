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

import java.util.Collections;
import java.util.List;

/**
 * Created by harishananth on 20/12/16.
 */

public class splrecyadapter extends RecyclerView.Adapter<splView_Holder> {

    List<spldata> list = Collections.emptyList();
    Context context;

    public splrecyadapter(List<spldata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public splView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.splchildrencard, parent, false);
        splView_Holder holder = new splView_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final splView_Holder holder, final int position) {

        final spldata item=list.get(position);

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        //holder.description.setText(list.get(p
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click image" + position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.textoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,holder.textoption);
                popupMenu.inflate((R.menu.option_menu));
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.learnmore:
                                Toast.makeText(context,"learn more "+position, Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

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
    public void insert(int position, spldata spldata) {
        list.add(position, spldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(spldata spldata) {
        int position = list.indexOf(spldata);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
