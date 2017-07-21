package kudwi.schoolex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by harishananth on 21/06/17.
 */
public class topView_Holder extends RecyclerView.ViewHolder {


    CardView cv;
    TextView title;
    RatingBar ratingBar;
    ImageView imageView;

    topView_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardViewtop);
        title = (TextView) itemView.findViewById(R.id.toptxt);
        imageView = (ImageView) itemView.findViewById(R.id.schoolimg);
        ratingBar=(RatingBar)itemView.findViewById(R.id.reviewrate);

    }
}