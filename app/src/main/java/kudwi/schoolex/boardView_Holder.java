package kudwi.schoolex;

/**
 * Created by harishananth on 20/06/17.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class boardView_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title,desc;
    ImageView imageView;

    boardView_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView2);
        title = (TextView) itemView.findViewById(R.id.titlemc);
        desc=(TextView)itemView.findViewById(R.id.decpt);
        imageView = (ImageView) itemView.findViewById(R.id.schoolimg);
    }
}