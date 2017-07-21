package kudwi.schoolex;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Splchildren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splchildren);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        List<spldata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewspl);
        recyclerView.setNestedScrollingEnabled(false);
        splrecyadapter adapter = new splrecyadapter(data,Splchildren.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

       /* recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Splchildren.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Toast.makeText(Splchildren.this,"Boys schools",Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(Splchildren.this,"Girls schools",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(Splchildren.this,"Co ed Schools",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                Toast.makeText(Splchildren.this,"item one long",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );*/

    }
    public List<spldata> fill_with_data() {

        List<spldata> data = new ArrayList<>();

        data.add(new spldata("Autism Centres", R.drawable.image));
        data.add(new spldata("Deaf and Dumb", R.drawable.defndumb));
        data.add(new spldata("Differently abled", R.drawable.disabled));
        data.add(new spldata("Blind schools", R.drawable.blind));

        return data;
    }
}
