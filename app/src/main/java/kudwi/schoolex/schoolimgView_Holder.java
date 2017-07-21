package kudwi.schoolex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by harishananth on 28/06/17.
 */

public class schoolimgView_Holder extends RecyclerView.ViewHolder{
    CardView cv;
    ImageView imageView;
    public schoolimgView_Holder(View Itemview)
    {
        super(Itemview);
cv=(CardView)Itemview.findViewById(R.id.cardViewschool);
        imageView=(ImageView)Itemview.findViewById(R.id.schoolimg);

    }
}
