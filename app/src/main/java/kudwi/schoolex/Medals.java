package kudwi.schoolex;

/**
 * Created by harishananth on 26/06/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Medals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medals);




        List<Medaldata> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewrel);
        recyclerView.setNestedScrollingEnabled(false);
        Medalrecyadapter adapter = new Medalrecyadapter(data,Medals.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);



    }
    public List<Medaldata> fill_with_data() {

        List<Medaldata> data = new ArrayList<>();

        data.add(new Medaldata("Gold", R.drawable.biggold,"The Kudwi Gold Medal is awardewd to schools that score the highest in terms of infrastructure,relative performance in academics and student support"));
        data.add(new Medaldata("Silver", R.drawable.bigsilver,"Silver Medal holders include schools that were once gold badge holders and those that are on the way to become one ,these schools pose a good academic performance but the infrastructure and student life has scope for improvement "));
        data.add(new Medaldata("Bronze", R.drawable.bigbronze,"Bronze Medal is awarded to schools that can do well in terms of academic performance and infrastructure."));

        return data;
    }
}
