package kudwi.schoolex;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harishananth on 30/06/17.
 */

public class Medalview_holder extends RecyclerView.ViewHolder{
    CardView cv;
    ImageView imageView;
    TextView medaltitle,subtitle;
    public Medalview_holder(View itemview)
    {
        super(itemview);
        cv=(CardView)itemview.findViewById(R.id.medalcard);
        imageView=(ImageView)itemview.findViewById(R.id.medalimg);
        medaltitle=(TextView)itemview.findViewById(R.id.medaltitle);
        subtitle=(TextView)itemview.findViewById(R.id.subtitle);

    }

}
