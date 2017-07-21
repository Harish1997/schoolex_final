package kudwi.schoolex;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import java.util.ArrayList;
import java.util.List;

public class schoolact extends AppCompatActivity {

    ViewPager viewPager;
    public static String school,name;
    public static FloatingActionButton fab;

    private TabLayout tablayout;
    int[] colorIntArray = {R.color.blue,R.color.orange};
    int[] iconIntArray = {R.drawable.iconchat,R.drawable.penicon};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolact);
        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            school = extras.getString("school");
            name=extras.getString("name");
        }
        setTitle("\t\t\t\t\t"+school);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("\t\t\t\t\t\t\tTap icon to chat");
        viewPager=(ViewPager)findViewById(R.id.pager);
        setupViewPager(viewPager);
        tablayout=(TabLayout)findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);
        fab=(FloatingActionButton)findViewById(R.id.fabschool);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(schoolact.this,MainActivitychat.class);
                 intent.putExtra("chattype","The PSBB Millennium School Gerugambakkam chat");
                 startActivity(intent);
             }
         });


        if(tablayout!=null)
            tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    switch(tab.getPosition())
                    {
                        case 0:
                        { getSupportActionBar().setSubtitle("\t\t\t\t\t\t\tTap icon to chat");
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(schoolact.this,MainActivitychat.class);
                                    intent.putExtra("chattype","The PSBB Millennium School Gerugambakkam chat");
                                    startActivity(intent);
                                }
                            });
                            break;
                        }
                        case 1:
                        {
                            getSupportActionBar().setSubtitle("\t\t\t\t\t\t\tTap icon to write a review");
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(schoolact.this,Writereview.class);
                                    startActivity(intent);
                                }
                            });
                            break;
                        }
                    }
                    animateFab(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPageAdapter viewpageadapter=new ViewPageAdapter(getSupportFragmentManager());
        viewpageadapter.add(new Fragment_1(),"About");
        viewpageadapter.add(new Fragment_2(),"Reviews");
        viewPager.setAdapter(viewpageadapter);
    }

    private class ViewPageAdapter extends FragmentPagerAdapter
    {
        List<Fragment> fragmentlist= new ArrayList<>();
        List<String> fragmenttitle=new ArrayList<>();
        public ViewPageAdapter(FragmentManager supportFragmentManager)
        {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }

        public void add(Fragment fragment, String title)
        {
            fragmentlist.add(fragment);
            fragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuinfalter=getMenuInflater();
        menuinfalter.inflate(R.menu.menu_schoolact,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id== R.id.action_settings)
        {

        }
        return super.onOptionsItemSelected(item);
    }

    protected void animateFab(final int position) {
        fab.clearAnimation();
        // Scale down animation
        ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }


            @Override
            public void onAnimationEnd(Animation animation) {
                // Change FAB color and icon
                fab.setBackgroundTintList(getResources().getColorStateList(colorIntArray[position]));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    fab.setImageDrawable(getResources().getDrawable(iconIntArray[position], null));
                }

                // Scale up animation
                ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);     // animation duration in milliseconds
                expand.setInterpolator(new AccelerateInterpolator());
                fab.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.startAnimation(shrink);
    }

}
