package kudwi.schoolex;

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

public class Boardchooser extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        setTitle("SchooleX - Choose a board");

        List<boarddata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        boardrecyadapter adapter = new boardrecyadapter(data,Boardchooser.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Boardchooser.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Toast.makeText(Boardchooser.this,"CBSE", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Boardchooser.this,Areachooser.class);
                                intent.putExtra("array_value",8);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(Boardchooser.this,"STATE", Toast.LENGTH_SHORT).show();
                                Intent intent1=new Intent(Boardchooser.this,Areachooser.class);
                                intent1.putExtra("array_value",7);
                                startActivity(intent1);
                                break;
                            case 2:
                                Toast.makeText(Boardchooser.this,"ICSE", Toast.LENGTH_SHORT).show();
                                Intent intent2=new Intent(Boardchooser.this,Areachooser.class);
                                intent2.putExtra("array_value",6);
                                startActivity(intent2);
                                break;
                            case 3:
                                Toast.makeText(Boardchooser.this,"IGCSE", Toast.LENGTH_SHORT).show();
                                Intent intent3=new Intent(Boardchooser.this,Areachooser.class);
                                intent3.putExtra("array_value",5);
                                startActivity(intent3);
                                break;
                            case 4:
                                Toast.makeText(Boardchooser.this,"IB", Toast.LENGTH_SHORT).show();
                                Intent intent4=new Intent(Boardchooser.this,Areachooser.class);
                                intent4.putExtra("array_value",4);
                                startActivity(intent4);
                                break;
                        }
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                Toast.makeText(Boardchooser.this,"item one long", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }
    public List<boarddata> fill_with_data() {

        List<boarddata> data = new ArrayList<>();

        data.add(new boarddata("CBSE", "\nThe Central Board of Secondary Education (abbreviated as CBSE) is a Board of Education for public and private schools, under the Union Government of India.", R.drawable.cbselogo));
        data.add(new boarddata("STATE", "\nTamil Nadu Board of Secondary Education, established in 1910, is under the purview of the Department of Education, Government of Tamil Nadu, India.", R.drawable.statelogo));
        data.add(new boarddata("ICSE", "\n" +
                "Council for the Indian School Certificate Examinations (abbreviated as CISCE) is a national level, private,Board of School education in India that conducts the Indian Certificate of Secondary Education and the Indian School Certificate examinations for Class X and Class XII respectively.It was established in 1958.", R.drawable.icselogo));
        return data;
    }
}
