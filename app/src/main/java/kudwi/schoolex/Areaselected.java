package kudwi.schoolex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by harishananth on 08/07/17.
 */

public class Areaselected extends AppCompatActivity {
    String area;
    TextView oops;
    private ProgressDialog progDailog;
    List<String> schoolnames=new ArrayList<String>();
    Set<String> hs=new HashSet<>();
    RecyclerView recyclerView;

    private DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areaselected);
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
        {
area=extras.getString("area");
        }
        setTitle("schools in\t"+area);
oops=(TextView)findViewById(R.id.oopstext);
        oops.setVisibility(View.INVISIBLE);
        root= FirebaseDatabase.getInstance().getReferenceFromUrl("https://schoolex-1c518.firebaseio.com/").child(area);

        progDailog = ProgressDialog.show(Areaselected.this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public List<sndata> fill_with_data() {

        List<sndata> data = new ArrayList<>();

       hs.addAll(schoolnames);
        schoolnames.clear();
        schoolnames.addAll(hs);

        for(int i=0;i<schoolnames.size();i++)
        {

            data.add(new sndata(schoolnames.get(i)));
        }
        return data;
    }

    String titl;
    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {



        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
            titl= (String) ((DataSnapshot)i.next()).getValue();
            if(titl.equals("Nil"))
            {
                oops.setVisibility(View.VISIBLE);
                oops.setText("Oops :( seems like there aren't any schools in "+area);
            }
            else {
                //Toast.makeText(Areaselected.this,"title"+titl,Toast.LENGTH_SHORT).show();
                schoolnames.add(titl);
                oops.setVisibility(View.INVISIBLE);
            }
        }
        List<sndata> sndatas = fill_with_data();
        snrecyadapter adapter = new snrecyadapter(sndatas,Areaselected.this);

        recyclerView = (RecyclerView)findViewById(R.id.schoolnamerecy);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Areaselected.this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        progDailog.dismiss();
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Areaselected.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            default:
                                Intent intent=new Intent(Areaselected.this,schoolact.class);
                                intent.putExtra("school",schoolnames.get(position));
                                intent.putExtra("name",MainActivity.uname);
                                startActivity(intent);
                                break;
                        }
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                //Toast.makeText(getActivity(),"item one long",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }


}
