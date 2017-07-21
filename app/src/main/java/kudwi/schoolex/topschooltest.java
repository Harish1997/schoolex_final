package kudwi.schoolex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harishananth on 22/06/17.
 */

public class topschooltest extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testhorizontal);
        List<topdata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.topschoolrecy);
        recyclerView.setNestedScrollingEnabled(false);
        toprecyadapter adapter = new toprecyadapter(data, topschooltest.this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(topschooltest.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);


        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(topschooltest.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                Toast.makeText(topschooltest.this, "Hinduism", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(topschooltest.this, "Christianity", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(topschooltest.this, "Islam", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        switch (position) {
                            case 1:
                                Toast.makeText(topschooltest.this, "item one long", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }

    public List<topdata> fill_with_data() {

        List<topdata> data = new ArrayList<>();

        data.add(new topdata("Hinduism", R.drawable.hindu,"http://www.psbbmillenniumschool.org/psbbmillennium/App_Themes/PsbbMillennium/images/gbkm-school.jpg",(float)5));
        data.add(new topdata("Hinduism", R.drawable.hindu,"http://www.psbbmillenniumschool.org/psbbmillennium/App_Themes/PsbbMillennium/images/gbkm-school.jpg",(float)5));
        data.add(new topdata("Hinduism", R.drawable.hindu,"http://www.psbbmillenniumschool.org/psbbmillennium/App_Themes/PsbbMillennium/images/gbkm-school.jpg",(float)5));


        return data;
    }
}
