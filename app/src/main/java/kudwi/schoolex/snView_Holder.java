package kudwi.schoolex;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by harishananth on 08/07/17.
 */

public class snView_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView schoolname;
    public snView_Holder(View itemView) {
        super(itemView);
        cv=(CardView)itemView.findViewById(R.id.cardViewsn);
        schoolname=(TextView)itemView.findViewById(R.id.schoolnamesn);

    }
}
