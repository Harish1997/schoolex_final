package kudwi.schoolex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class religion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religion);




        List<spldata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewrel);
        recyclerView.setNestedScrollingEnabled(false);
        splrecyadapter adapter = new splrecyadapter(data,religion.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

       recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(religion.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Toast.makeText(religion.this,"Hinduism", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(religion.this,"Christianity", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(religion.this,"Islam", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                Toast.makeText(religion.this,"item one long", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }
    public List<spldata> fill_with_data() {

        List<spldata> data = new ArrayList<>();

        data.add(new spldata("Hinduism", R.drawable.hindu));
        data.add(new spldata("Christianity", R.drawable.christ));
        data.add(new spldata("Islam", R.drawable.islam));

        return data;
    }
}
