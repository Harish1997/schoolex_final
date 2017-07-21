package kudwi.schoolex;

/**
 * Created by harishananth on 20/06/17.
 */

import android.content.Intent;
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
 * Created by harishananth on 20/06/17.
 */

public class Genderchooser extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genderchooser);
        setTitle("SchooleX - Choose a Category");

        List<boarddata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewgender);
        boardrecyadapter adapter = new boardrecyadapter(data,Genderchooser.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Genderchooser.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Intent intent=new Intent(Genderchooser.this,Areachooser.class);
                                intent.putExtra("array_value",2);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(Genderchooser.this, "Girls schools", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(Genderchooser.this, Areachooser.class);
                                intent1.putExtra("array_value", 1);
                                startActivity(intent1);
                                break;

                            case 2:
                                Toast.makeText(Genderchooser.this,"Co ed Schools", Toast.LENGTH_SHORT).show();
                                Intent intent2=new Intent(Genderchooser.this,Areachooser.class);
                                intent2.putExtra("array_value",3);
                                startActivity(intent2);
                                break;
                        }
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                Toast.makeText(Genderchooser.this,"item one long", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }
    public List<boarddata> fill_with_data() {

        List<boarddata> data = new ArrayList<>();

        data.add(new boarddata("Boys school", "\n Dig deeper to know more on boys only schools\n These schools offer utmost regard to discipline and strong morals,most of the schools are also accompanied with hostel facilities", R.drawable.boy));
        data.add(new boarddata("Girls school", "\n These schools tend to have the highest pass percentage ratio and many of them hold an average of above 80% in results.\n A heaven for geeks", R.drawable.girl));
        data.add(new boarddata("Co-ed school", "\n" +
                "Students of co ed schools tend to be more outspoken than the other categories,these students inherit strong leadership and creativity skils which sets them apart.\n Studies have also proved that they are the ones who tend to be more successful in life!", R.drawable.coed));
        return data;
    }
}