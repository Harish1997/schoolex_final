package kudwi.schoolex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by harishananth on 15/06/17.
 */

public class Displaycred extends AppCompatActivity {
   TextView name;
    EditText email;
    EditText phonenumber;
    String uphone;
    CircleImageView profpic;
    Button proceed;
    String city;
    private PrefManager prefManager;
    String uname,uemail,url;
    Spinner spinner;
    SharedPreferences sharedPref;
    Button next;
    String item;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if(MainActivity.exitval==true)
        {
            finish();
            System.exit(0);
        }*/

        sharedPref = getSharedPreferences("userinfo", MODE_PRIVATE);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            sharedPref = getSharedPreferences("userinfo", MODE_PRIVATE);
            {
                if (sharedPref != null) {
                    uname = sharedPref.getString("name", "name");
                    uemail = sharedPref.getString("email", "email");
                    url = sharedPref.getString("url", "url");
                    uphone = sharedPref.getString("phone", "phone");
                    city = sharedPref.getString("city", "city");
                    Intent intent = new Intent(Displaycred.this, MainActivity.class);
                    intent.putExtra("name", uname);
                    intent.putExtra("email", uemail);
                    intent.putExtra("url", url);
                    intent.putExtra("city", city);
                    Toast.makeText(Displaycred.this,"city is "+city,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
            finish();
        }
        setContentView(R.layout.displaycred);
        setTitle("Hello!");

        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            uname = extras.getString("username");
            uemail = extras.getString("email");
            url = extras.getString("url");
        }


        profpic=(CircleImageView)findViewById(R.id.profile_image);
        name=(TextView)findViewById(R.id.username);
        email=(EditText) findViewById(R.id.email);
        phonenumber=(EditText) findViewById(R.id.phonenum);

        proceed=(Button)findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uphone=phonenumber.getText().toString();
                uemail=email.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email",uemail);
                editor.putString("phone",uphone);
                editor.putString("name",uname);
                editor.putString("url",url);
                editor.putString("flag","1");
                editor.apply();
Savedata();
                sendEmail(uname,uemail,uphone);
                Intent intent=new Intent(Displaycred.this,WelcomeActivity.class);
                intent.putExtra("name",uname);
                intent.putExtra("email",uemail);
                intent.putExtra("url",url);
                startActivity(intent);


            }
        });
        spinner=(Spinner)findViewById(R.id.spinner);
        List<String> cities=new ArrayList<>();
        cities.add("Pick a city");
        cities.add("Chennai");
        cities.add("Mumbai -Coming soon");
        cities.add("Delhi -Coming soon");
        cities.add("Banglore -Coming soon");
        cities.add("Kolkata -Coming soon");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cities);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                item = spinner.getItemAtPosition(i).toString();

                // Showing selected spinner item
                //Toast.makeText(Citychooser.this, "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        // if(uname!=null && uemail!=null && uphone!=null && url!=null)
        {

            name.setText(uname);

            email.setText(uemail);

            Glide.with(Displaycred.this).load(url).into(profpic);
        }




    }
    private void sendEmail(String uname, String uemail, String uphone) {
        //Getting content for email
        String email =uemail;
        String subject = "Thank you for signing up - Paul Arun -Founder,SchooleX";
        String message = "Hello\t"+uname+"\n"+
                "\n" +
                "Hope you're doing fantastic! I noticed that you just signed up at SchooleX, and I wanted to personally thank you. I'm Paul Arun the founder/CEO and my goal is to offer the best experience possible to users. \n\n  Cheers \n paul Arun";

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
    public void Savedata()
    {
        sharedPref=getSharedPreferences("userinfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("city",item);
        editor.apply();
    }


    private void launchHomeScreen() {
        Intent intent=new Intent(Displaycred.this,MainActivity.class);
        intent.putExtra("name",uname);
        intent.putExtra("email",uemail);
        intent.putExtra("url",url);
        intent.putExtra("city",item);
        startActivity(intent);
        finish();
    }
}