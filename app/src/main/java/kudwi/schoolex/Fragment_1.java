package kudwi.schoolex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by harishananth on 22/06/17.
 */

public class Fragment_1 extends Fragment{
    TextView notifytext;
    String url1,url2,url3,url4;
    Switch aSwitch;
    private DatabaseReference root;
    Button map;
    private ProgressDialog progDailog;


    ProgressBar progressBar;
    TextView resedential,area,address,board,name,gender,languages,facilities,integrated,sports,religion,transport,admission,website,email,contact,classes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_atest,null);
        map=(Button)view.findViewById(R.id.mapbutton);
        area=(TextView)view.findViewById(R.id.areatxt);
        address=(TextView)view.findViewById(R.id.addresstxt);
        board=(TextView)view.findViewById(R.id.boardtxt);
        classes=(TextView)view.findViewById(R.id.classestxt);
        name=(TextView)view.findViewById(R.id.nametxt);
        gender=(TextView)view.findViewById(R.id.gendertxt);
        languages=(TextView)view.findViewById(R.id.languagestxt);
        facilities=(TextView)view.findViewById(R.id.facilitytxt);
        integrated=(TextView)view.findViewById(R.id.integratedtxt);
        sports=(TextView)view.findViewById(R.id.sportstxt);
        religion=(TextView)view.findViewById(R.id.religiontxt);
        transport=(TextView)view.findViewById(R.id.transportxt);
        admission=(TextView)view.findViewById(R.id.admdatestxt);
        website=(TextView)view.findViewById(R.id.websitetxt);
        email=(TextView)view.findViewById(R.id.emailtxt);
        resedential=(TextView)view.findViewById(R.id.restxt);
        contact=(TextView)view.findViewById(R.id.contactxt);
        root = FirebaseDatabase.getInstance().getReferenceFromUrl("https://schoolex-1c518.firebaseio.com/Schools").child(schoolact.school);


        progDailog = ProgressDialog.show(getActivity(), "Loading", "Please wait...", true);
        progDailog.setCancelable(false);

        notifytext=(TextView)view.findViewById(R.id.notifymetext);
        aSwitch=(Switch)view.findViewById(R.id.switchnotify);
        if(schoolact.school!=null)
        notifytext.setText("Notify me for admissions in\t\n"+schoolact.school);
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


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(aSwitch.isChecked()) {
                    notifytext.setTextColor(Color.rgb(27, 94, 32));
                    notifytext.setText("Sounds good! Our team will keep you updated on the admission details via Email or phone");
                } else {
                    notifytext.setTextColor(Color.BLACK);
                    notifytext.setText("Notify me for admissions in\t\n" + schoolact.school);
                }

            }
        });

        List<Imgdata> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.schoolrecycler);
        recyclerView.setNestedScrollingEnabled(false);
        schoolimgrecyadapter adapter = new schoolimgrecyadapter(data,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        return view;
    }

    public List<Imgdata> fill_with_data() {

        List<Imgdata> data = new ArrayList<>();

        data.add(new Imgdata(R.drawable.hindu,getUrl1));
        data.add(new Imgdata(R.drawable.christ,getUrl2));
        data.add(new Imgdata(R.drawable.christ,getUrl3));
        data.add(new Imgdata(R.drawable.christ,getUrl4));

        return data;
    }

    String  resedentialst,getUrl1,getUrl2,getUrl3,getUrl4,namest,areast,addressst,boardst,classesst,genderst,languagesst,facilitiesst,integratedst,sportsst,religionst,transportst,admissionst,websitest,emailst,contactst;
Double lat,lon;
    private void append_chat_conversation(DataSnapshot dataSnapshot,View view)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
                        addressst= (String) ((DataSnapshot)i.next()).getValue();
            admissionst= (String) ((DataSnapshot)i.next()).getValue();
            areast= (String) ((DataSnapshot)i.next()).getValue();
            boardst= (String) ((DataSnapshot)i.next()).getValue();
            classesst=(String) ((DataSnapshot)i.next()).getValue();
            contactst= (String) ((DataSnapshot)i.next()).getValue();
            emailst= (String) ((DataSnapshot)i.next()).getValue();
            facilitiesst= (String) ((DataSnapshot)i.next()).getValue();
            genderst= (String) ((DataSnapshot)i.next()).getValue();
            integratedst= (String) ((DataSnapshot)i.next()).getValue();
            languagesst= (String) ((DataSnapshot)i.next()).getValue();
            lat= (Double) ((DataSnapshot)i.next()).getValue();
            lon= (Double) ((DataSnapshot)i.next()).getValue();
            namest= (String) ((DataSnapshot)i.next()).getValue();
            religionst= (String) ((DataSnapshot)i.next()).getValue();
            resedentialst=(String)((DataSnapshot)i.next()).getValue();
            sportsst= (String) ((DataSnapshot)i.next()).getValue();
            transportst= (String) ((DataSnapshot)i.next()).getValue();
            getUrl1= (String) ((DataSnapshot)i.next()).getValue();
            getUrl2= (String) ((DataSnapshot)i.next()).getValue();
            getUrl3= (String) ((DataSnapshot)i.next()).getValue();
            getUrl4= (String) ((DataSnapshot)i.next()).getValue();
            websitest= (String) ((DataSnapshot)i.next()).getValue();
        }
area.setText(areast);
        classes.setText(classesst);
        address.setText(addressst);
        board.setText(boardst);
        name.setText(namest);
        resedential.setText(resedentialst);
        gender.setText(genderst);
        languages.setText(languagesst);
        facilities.setText(facilitiesst);
        integrated.setText(integratedst);
        sports.setText(sportsst);
        religion.setText(religionst);
        transport.setText(transportst);
        admission.setText(admissionst);
        website.setText(websitest);
        email.setText(emailst);
        contact.setText(contactst);

        {
            List<Imgdata> data = fill_with_data();

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.schoolrecycler);
            recyclerView.setNestedScrollingEnabled(false);
            schoolimgrecyadapter adapter = new schoolimgrecyadapter(data,getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);

            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);
        }

        progDailog.dismiss();
map.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String label=namest;
        String uriBegin = "geo:" + lat + "," + lon;
        String query = lat + "," + lon + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});


    }
}
