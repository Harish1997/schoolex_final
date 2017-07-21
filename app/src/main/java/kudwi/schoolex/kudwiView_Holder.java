package kudwi.schoolex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by harishananth on 25/06/17.
 */

public class kudwiView_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView name,review;
    RatingBar ratingBar;
    public kudwiView_Holder(View itemView) {
        super(itemView);
        cv=(CardView)itemView.findViewById(R.id.cardViewreview);
        name=(TextView)itemView.findViewById(R.id.namereview);
        review=(TextView)itemView.findViewById(R.id.review);
        ratingBar=(RatingBar)itemView.findViewById(R.id.reviewrate);


    }
}
