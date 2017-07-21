package kudwi.schoolex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by harishananth on 22/06/17.
 */

public class Fragment_2 extends Fragment {
    private DatabaseReference root;
    public RecyclerView recyclerView;
ImageView medal;
    List<String> nameslist=new ArrayList<String>();
    List<String> reviewslist=new ArrayList<String>();
    List<Float> ratinglist=new ArrayList<Float>();
    public String kudwireview;
    public Float kudwirate=(float)4;

    Set<String> hs=new HashSet<>();
    Set<String> hsstring=new HashSet<>();
    Set<Float> hsfloat=new HashSet<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_b, null);
        medal= (ImageView) view.findViewById(R.id.medalimg);
        root = FirebaseDatabase.getInstance().getReferenceFromUrl("https://schoolex-1c518.firebaseio.com/Schoolreview").child(schoolact.school);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot,view);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot,view);
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


        return view;
    }

    public List<kudwidata> fillwithreviews() {

        List<kudwidata> data = new ArrayList<>();
       /* hs.addAll(nameslist);
        nameslist.clear();
        nameslist.addAll(hs);

        hsstring.addAll(reviewslist);
        reviewslist.clear();
        reviewslist.addAll(hsstring);
        hsfloat.addAll(ratinglist);
        ratinglist.clear();
        ratinglist.addAll(hsfloat);*/

        for(int i=0;i<nameslist.size();i++) {
            data.add(new kudwidata(nameslist.get(i),reviewslist.get(i), ratinglist.get(i)));

        }
        return data;
    }

    public List<kudwidata> fillwithkudwireview() {

        List<kudwidata> data = new ArrayList<>();


        data.add(new kudwidata("Our review",kudwireview,kudwirate));

        return data;
    }

    String name,review;
    String rating;
    private void append_chat_conversation(DataSnapshot dataSnapshot,View view)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {


            name= (String) ((DataSnapshot)i.next()).getValue();
            rating= (String) ((DataSnapshot)i.next()).getValue();
            review= (String) ((DataSnapshot)i.next()).getValue();
            if(review.contains("kudwi") ) {
                if(name.contains("gold"))
                {
                    medal.setImageResource(R.drawable.biggold);
                }
                else if(name.contains("silver"))
                {
                    medal.setImageResource(R.drawable.bigsilver);
                }
                else
                {
                    medal.setImageResource(R.drawable.olympic_medal_bronze_icon);
                }

                kudwireview = review.replaceAll("kudwi", "");
                kudwirate = Float.valueOf(rating);
            }
                else
                {
                    nameslist.add(name);
                    reviewslist.add(review);
                    ratinglist.add(Float.valueOf(rating));
                }

            }
        {
            List<kudwidata> data = fillwithreviews();

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.userreviewrecy);
            recyclerView.setNestedScrollingEnabled(false);
            kudwirecyadapter adapter = new kudwirecyadapter(data, getActivity());
            recyclerView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);


            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (position) {
                                case 0:

                                    break;
                                case 1:
                                    Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            // do whatever
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            switch (position) {
                                case 1:
                                    Toast.makeText(getActivity(), "item one long", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            // do whatever
                        }
                    })
            );

        }

        {
            List<kudwidata> data = fillwithkudwireview();

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.kudwireview);
            recyclerView.setNestedScrollingEnabled(false);
            kudwirecyadapter adapter = new kudwirecyadapter(data, getActivity());
            recyclerView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);


            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);
        }

    }
}
