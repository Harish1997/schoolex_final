package kudwi.schoolex;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView profimg;
    TextView name,email;
   public static String uname,uemail,url;
    private View navheader;
    private ListView lv;
    WebView view;
public static boolean exitval=false;
    Button area,board,gender,resedential,religion,coaching,splchildren;
    // Listview Adapter
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exitval=true;

        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            uname = extras.getString("name");
            uemail = extras.getString("email");
            url = extras.getString("url");
        }
        view=(WebView)findViewById(R.id.mywebview);
        area=(Button)findViewById(R.id.areacat);
        board=(Button)findViewById(R.id.boardcat);
        gender=(Button)findViewById(R.id.gendercat);
        resedential=(Button)findViewById(R.id.hostelcat);
        religion=(Button)findViewById(R.id.religioncat);
        coaching=(Button)findViewById(R.id.coachingcat);
        splchildren=(Button)findViewById(R.id.splcat);



        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("https://www.google.co.in/maps/search/schools/@13.0633872,80.2265543,15z/data=!3m1!4b1"); //try js alert
        view.setWebChromeClient(new WebChromeClient()); // adding js alert support





        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Areachooser.class);
                intent.putExtra("array_value",9);
                startActivity(intent);
            }
        });

        board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Boardchooser.class);
                startActivity(intent);
            }
        });

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Genderchooser.class);
                startActivity(intent);
            }
        });
        resedential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Areachooser.class);
                intent.putExtra("array_value",12);
                startActivity(intent);
            }
        });
        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,religion.class);
                startActivity(intent);
            }
        });
        coaching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Boardchooser.class);
                startActivity(intent);
            }
        });
        splchildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Splchildren.class);
                startActivity(intent);
            }
        });


        lv=(ListView)findViewById(R.id.listView);
        lv.setVisibility(View.INVISIBLE);
        ArrayList<String> arrayschools=new ArrayList<>();
        arrayschools.addAll(Arrays.asList(getResources().getStringArray(R.array.array_schools)));
        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrayschools);
        lv.setAdapter(adapter);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setVisibility(View.VISIBLE);
            }
        });
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("\tSchooleX");
        getSupportActionBar().setSubtitle("\t\tkudwi");
        getSupportActionBar().setIcon(R.drawable.ic_action_name);

        {
            List<topdata> data = fill_with_datacbse();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.topschoolrecycbse);
            recyclerView.setNestedScrollingEnabled(false);
            toprecyadapter adapter = new toprecyadapter(data, MainActivity.this);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);


            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (position) {
                                case 0:
                                    Intent intent=new Intent(MainActivity.this,schoolact.class);
                                    intent.putExtra("school","The PSBB Millennium School Gerugambakkam");
                                    intent.putExtra("name",uname);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1=new Intent(MainActivity.this,schoolact.class);
                                    intent1.putExtra("school","DAV Boys Senior Secondary School Gopalapuram");
                                    intent1.putExtra("name",uname);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2=new Intent(MainActivity.this,schoolact.class);
                                    intent2.putExtra("school","Chettinad Vidyashram");
                                    intent2.putExtra("name",uname);
                                    startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3=new Intent(MainActivity.this,schoolact.class);
                                    intent3.putExtra("school","Vidya Mandir Senior Secondary School Mylapore");
                                    intent3.putExtra("name",uname);
                                    startActivity(intent3);
                                    break;
                                case 4:
                                    Intent intent4=new Intent(MainActivity.this,schoolact.class);
                                    intent4.putExtra("school","PS Senior Secondary School");
                                    intent4.putExtra("name",uname);
                                    startActivity(intent4);
                                    break;
                                case 5:
                                    Intent intent5=new Intent(MainActivity.this,schoolact.class);
                                    intent5.putExtra("school","Sishya School");
                                    intent5.putExtra("name",uname);
                                    startActivity(intent5);
                                    break;
                                case 6:
                                    Intent intent6=new Intent(MainActivity.this,schoolact.class);
                                    intent6.putExtra("school","Bharatiya Vidhya Bhavans Rajaji Vidyashram");
                                    intent6.putExtra("name",uname);
                                    startActivity(intent6);
                                    break;
                                case 7:
                                    Intent intent7=new Intent(MainActivity.this,schoolact.class);
                                    intent7.putExtra("school","National Public School Gopalapuram");
                                    intent7.putExtra("name",uname);
                                    startActivity(intent7);
                                    break;
                                case 8:
                                    Intent intent8=new Intent(MainActivity.this,schoolact.class);
                                    intent8.putExtra("school","DAV Boys Senior Secondary School Mogappair");
                                    intent8.putExtra("name",uname);
                                    startActivity(intent8);
                                    break;
                                case 9:
                                    Intent intent9=new Intent(MainActivity.this,schoolact.class);
                                    intent9.putExtra("school","Maharishi Vidya Mandir Senior Secondary School Chetpet");
                                    intent9.putExtra("name",uname);
                                    startActivity(intent9);
                                    break;

                            }
                            // do whatever
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            switch (position) {
                                case 1:
                                  //  Toast.makeText(MainActivity.this, "item one long", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            // do whatever
                        }
                    })
            );
        }

        {
            List<topdata> data = fill_with_datastate();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.topschoolrecystate);
            recyclerView.setNestedScrollingEnabled(false);
            toprecyadapter adapter = new toprecyadapter(data, MainActivity.this);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);


            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (position) {
                                case 0:
                                    Intent intent=new Intent(MainActivity.this,schoolact.class);
                                    intent.putExtra("school","Velammal Matriculation Higher Secondary School Mogappair");
                                    intent.putExtra("name",uname);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1=new Intent(MainActivity.this,schoolact.class);
                                    intent1.putExtra("school","DAV Matriculation Higher Secondary School-Mogappair");
                                    intent1.putExtra("name",uname);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2=new Intent(MainActivity.this,schoolact.class);
                                    intent2.putExtra("school","SBOA Matriculation and Higher Secondary School Anna Nagar");
                                    intent2.putExtra("name",uname);
                                    startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3=new Intent(MainActivity.this,schoolact.class);
                                    intent3.putExtra("school","Jaigopal Garodia Matriculation Higher Secondary School");
                                    intent3.putExtra("name",uname);
                                    startActivity(intent3);
                                    break;
                                case 4:
                                    Intent intent4=new Intent(MainActivity.this,schoolact.class);
                                    intent4.putExtra("school","Don Bosco Matriculation Higher Secondary School Kolathur");
                                    intent4.putExtra("name",uname);
                                    startActivity(intent4);
                                    break;
                                case 5:
                                    Intent intent5=new Intent(MainActivity.this,schoolact.class);
                                    intent5.putExtra("school","St Johns - Alwarthirunagar");
                                    intent5.putExtra("name",uname);
                                    startActivity(intent5);
                                    break;
                                case 6:
                                    Intent intent6=new Intent(MainActivity.this,schoolact.class);
                                    intent6.putExtra("school","Spartan Matric Higher Secondary School Mogappair East");
                                    intent6.putExtra("name",uname);
                                    startActivity(intent6);
                                    break;
                                case 7:
                                    Intent intent7=new Intent(MainActivity.this,schoolact.class);
                                    intent7.putExtra("school","Loyola Matriculation Higher Secondary School Kodambakkam");
                                    intent7.putExtra("name",uname);
                                    startActivity(intent7);
                                    break;
                                case 8:
                                    Intent intent8=new Intent(MainActivity.this,schoolact.class);
                                    intent8.putExtra("school","DAV Matriculation Higher Secondary School Gill Nagar");
                                    intent8.putExtra("name",uname);
                                    startActivity(intent8);
                                    break;
                                case 9:
                                    Intent intent9=new Intent(MainActivity.this,schoolact.class);
                                    intent9.putExtra("school","CSI Ewart Matriculation Higher Secondary School Purasawalkam");
                                    intent9.putExtra("name",uname);
                                    startActivity(intent9);
                                    break;
                            }
                            // do whatever
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            switch (position) {
                                case 1:
                                   // Toast.makeText(MainActivity.this, "item one long", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            // do whatever
                        }
                    })
            );
        }


        {
            List<topdata> data = fill_with_dataicse();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.topschoolrecyicse);
            recyclerView.setNestedScrollingEnabled(false);
            toprecyadapter adapter = new toprecyadapter(data, MainActivity.this);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);


            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (position) {
                                case 0:
                                    Intent intent=new Intent(MainActivity.this,schoolact.class);
                                    intent.putExtra("school","St Michaels Academy Matriculation Higher Secondary School Adyar");
                                    intent.putExtra("name",uname);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1=new Intent(MainActivity.this,schoolact.class);
                                    intent1.putExtra("school","St Francis International School");
                                    intent1.putExtra("name",uname);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2=new Intent(MainActivity.this,schoolact.class);
                                    intent2.putExtra("school","Vaels International School");
                                    intent2.putExtra("name",uname);
                                    startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3=new Intent(MainActivity.this,schoolact.class);
                                    intent3.putExtra("school","The School KFI");
                                    intent3.putExtra("name",uname);
                                    startActivity(intent3);
                                    break;
                                case 4:
                                    Intent intent4=new Intent(MainActivity.this,schoolact.class);
                                    intent4.putExtra("school","Sishya School");
                                    intent4.putExtra("name",uname);
                                    startActivity(intent4);
                                    break;
                                case 5:
                                    Intent intent5=new Intent(MainActivity.this,schoolact.class);
                                    intent5.putExtra("school","Chettinad Hari Shree Vidyalam");
                                    intent5.putExtra("name",uname);
                                    startActivity(intent5);
                                    break;
                                case 6:
                                    Intent intent6=new Intent(MainActivity.this,schoolact.class);
                                    intent6.putExtra("school","Abacus Montessori School");
                                    intent6.putExtra("name",uname);
                                    startActivity(intent6);
                                    break;
                                case 7:
                                    Intent intent7=new Intent(MainActivity.this,schoolact.class);
                                    intent7.putExtra("school","Primrose Schools");
                                    intent7.putExtra("name",uname);
                                    startActivity(intent7);
                                    break;
                                case 8:
                                    Intent intent8=new Intent(MainActivity.this,schoolact.class);
                                    intent8.putExtra("school","Grove School");
                                    intent8.putExtra("name",uname);
                                    startActivity(intent8);
                                    break;
                                case 9:
                                    Intent intent9=new Intent(MainActivity.this,schoolact.class);
                                    intent9.putExtra("school","Doveton Group Of Schools");
                                    intent9.putExtra("name",uname);
                                    startActivity(intent9);
                                    break;
                            }
                            // do whatever
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            switch (position) {
                                case 1:
                                   // Toast.makeText(MainActivity.this, "item one long", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            // do whatever
                        }
                    })
            );
        }





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,Writetous.class);
                intent.putExtra("name",uname);
                intent.putExtra("email",uemail);
                startActivity(intent);
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        navheader=navigationView.getHeaderView(0);
        name=(TextView)navheader.findViewById(R.id.unamenav);
        email=(TextView)navheader.findViewById(R.id.uemailnav);
        if(uname.equals("name")) {
            Intent intent=new Intent(MainActivity.this,Configprofile.class);
            startActivity(intent);

        }
        else
        {
            profimg = (CircleImageView) navheader.findViewById(R.id.profile_imagenav);
            name.setText(uname);
            email.setText(uemail);
            Glide.with(MainActivity.this).load(url).into(profimg);

        }

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //method goback() dieksekusi untuk kembali pada halaman sebelumnya
            return true;
        }
        // Jika tidak ada history (Halaman yang sebelumnya dibuka)
        // maka akan keluar dari activity
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                lv.setVisibility(View.INVISIBLE);

        }else {
            lv.setVisibility(View.INVISIBLE);
               this.finishAffinity();
                System.exit(0);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem=menu.findItem(R.id.menuSearch);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                lv.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                lv.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lv.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        lv.setVisibility(View.VISIBLE);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id== R.id.menuSearch)
        lv.setVisibility(View.VISIBLE);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.account) {
            // Handle the camera action
        } else if (id == R.id.medals) {
            Intent intent=new Intent(MainActivity.this,Medals.class);
            startActivity(intent);


        } else if (id == R.id.chat) {
            Intent intent=new Intent(MainActivity.this,MainActivitychat.class);
            intent.putExtra("chattype","Globalchat");
            startActivity(intent);



        } else if (id == R.id.contact) {
            Intent intent=new Intent(MainActivity.this,Contactkudwi.class);
            startActivity(intent);

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<topdata> fill_with_datacbse() {

        List<topdata> data = new ArrayList<>();

        data.add(new topdata("The PSBB Millennium School Gerugambakkam", R.drawable.hindu,"http://www.psbbmillenniumschool.org/psbbmillennium/App_Themes/PsbbMillennium/images/gbkm-school.jpg",(float)5));
        data.add(new topdata("D.A.V. Boys Senior Secondary School", R.drawable.hindu,"https://content2.jdmagicbox.com/chennai/59/044pf004559/catalogue/dav-boys-senior-secondary-school-chennai-699se.jpg",(float)5));
        data.add(new topdata("Chettinad Vidyashram", R.drawable.hindu,"http://chettinadvidyashram.org/wp-content/uploads/sites/6/2013/06/panschool.jpg",(float)5));
        data.add(new topdata("Vidya Mandir Senior Secondary School", R.drawable.hindu,"http://image3.mouthshut.com/images/Restaurant/Photo/-38303_73056.jpg",(float)5));
        data.add(new topdata("P.S. Senior Secondary School", R.drawable.hindu,"http://staging.pssenior.org/_/rsrc/1284665438655/photo-gallery/campus/B.jpg",(float)5));
        data.add(new topdata("Sishya School", R.drawable.hindu,"http://www.sishya.com/images/Index-Image.jpg",(float)5));
        data.add(new topdata("Bhavan's Rajaji Vidyashram", R.drawable.hindu,"https://www.vidteq.com/chennai/jpg/iq/iqhedDOVEEVPTCITVOTQO.jpg",(float)5));
        data.add(new topdata("National Public School,Chennai", R.drawable.hindu,"http://cdn2.momjunction.com/wp-content/uploads/2016/03/16National-Public-School.jpg",(float)5));
        data.add(new topdata("D.A.V(Boys) Senior Secondary Mogappair", R.drawable.hindu,"http://schoolconnects.s3.amazonaws.com/s3fs-public/dav.gif",(float)5));
        data.add(new topdata("Maharishi Vidya Mandir Senior Secondary", R.drawable.hindu,"http://kidscontests.in/wp-content/uploads/2013/11/maharishi-vidya-mandir-chennai.jpg",(float)5));

        return data;
    }

    public List<topdata> fill_with_datastate() {

        List<topdata> data = new ArrayList<>();



        data.add(new topdata("Velammal Matric - Mogappair", R.drawable.hindu,"http://www.velammalnexus.com/wp-content/uploads/2013/06/11.jpg",(float)5));
        data.add(new topdata("DAV Matric - Mogappair", R.drawable.hindu,"http://www.vidteq.com/chennai/jpg/iv/iveihIOVCOITTTIEVOTVO.jpg",(float)5));
        data.add(new topdata("SBOA Matric - Anna Nagar", R.drawable.hindu,"http://www.sboaschoolmdu.org/uploads/1/8/8/6/18861174/1785800_orig.jpg",(float)5));
        data.add(new topdata("Jaigopal Garodia - Perambur", R.drawable.hindu,"http://nie-images.s3.amazonaws.com/gall_content/2016/12/2016_12$schoolimg09_Dec_2016_171202800.JPG",(float)5));
        data.add(new topdata("Don Bosco - Kolathur", R.drawable.hindu,"http://donboscowt.org/content/images/DSC00233.JPG",(float)5));
        data.add(new topdata("St Johns - Alwarthirunagar", R.drawable.hindu,"https://upload.wikimedia.org/wikipedia/commons/d/db/St._John%27s_Matriculation_Higher_Secondary_School_Alwarthirunagar_1-8-2015_2-56-10_PM.jpg",(float)5));
        data.add(new topdata("Spartan Matric - Mogappair east", R.drawable.hindu,"http://spartanschools.com/wp-content/uploads/wow-slider-plugin/2/images/2.jpg",(float)5));
        data.add(new topdata("Loyola Matriculation - Kodambakkam", R.drawable.hindu,"https://s3-ap-southeast-1.amazonaws.com/tv-prod/school/photo/50145-large.jpg",(float)5));
        data.add(new topdata("DAV Matric - Gillnagar", R.drawable.hindu,"http://test.excitingip.net/wp-content/uploads/2016/02/DAV-Matric-Gill-Nagar-300x218.png",(float)5));
        data.add(new topdata("C.S.I Ewart Matric-Kilpauk", R.drawable.hindu,"https://chennaidailyfoto.files.wordpress.com/2009/04/m_039-014.jpg",(float)5));



        return data;
    }
    public List<topdata> fill_with_dataicse() {

        List<topdata> data = new ArrayList<>();



        data.add(new topdata("St.Michael's Academy", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%205.19.17%20PM.png?alt=media&token=ed2bfb62-dec9-4d40-abf6-477f1610469c", (float) 5));
        data.add(new topdata("St Francis International", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%204.34.49%20PM.png?alt=media&token=304a9236-8ad0-45d9-b943-359de1a784a9", (float) 5));
        data.add(new topdata("Vael's Billabong High International", R.drawable.hindu, "https://www.vidteq.com/chennai/jpg/ie/ieiotDOVEEOQDVIOMICCI.jpg", (float) 5));
        data.add(new topdata("The School - KFI", R.drawable.hindu, "http://www.kfionline.org/wp-content/uploads/2017/01/The-School-KFI.jpg", (float) 5));
        data.add(new topdata("Sishya School", R.drawable.hindu, "https://farm9.staticflickr.com/8678/15757600780_a4851049ff_b.jpg", (float) 5));
        data.add(new topdata("Chettinad Hari Shree Vidyalam", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%205.02.29%20PM.png?alt=media&token=6ddda449-e512-4073-af0f-422e037d26cc", (float) 5));
        data.add(new topdata("Abacus Montessori", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%205.04.17%20PM.png?alt=media&token=6f104331-c906-4402-a276-d39e8797654d", (float) 5));
        data.add(new topdata("Primorse School", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%205.08.37%20PM.png?alt=media&token=3424ee0b-5718-4437-899e-cb5f914681f6", (float) 5));
        data.add(new topdata("The Grove School", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%204.58.29%20PM.png?alt=media&token=81226e3a-60b8-49bd-8234-f250536529e2", (float) 5));
        data.add(new topdata("Doveton Group Of Schools", R.drawable.hindu, "https://firebasestorage.googleapis.com/v0/b/schoolex-1c518.appspot.com/o/Screen%20Shot%202017-06-19%20at%205.10.45%20PM.png?alt=media&token=3d212fda-8402-4b13-b146-cf17e796316a", (float) 5));

        return data;
    }
}
