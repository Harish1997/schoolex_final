package kudwi.schoolex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harishananth on 21/06/17.
 */

public class splView_Holder extends RecyclerView.ViewHolder {


    CardView cv;
    TextView title,textoption;
    ImageView imageView;

    splView_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.splcard);
        title = (TextView) itemView.findViewById(R.id.spltitle);
        imageView = (ImageView) itemView.findViewById(R.id.splimg);
        textoption=(TextView)itemView.findViewById(R.id.textoption);

    }
}