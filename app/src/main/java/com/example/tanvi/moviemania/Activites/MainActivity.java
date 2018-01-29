package com.example.tanvi.moviemania.Activites;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.TabHost;
import android.widget.TextView;



import com.example.tanvi.moviemania.Adapters.MyPagerAdapter;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.fragments.MoviesTab;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity implements MoviesTab.OnFragmentInteractionListener, MyPagerAdapter.InitaliseFragmentInterface {


    public static  String api_key ="cd458af3e465c915faedf516d4f513c0";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        Toolbar toolbar=findViewById(R.id.mainActivityToolbar);
        toolbar.setTitle("Movie Mania");
        setSupportActionBar(toolbar);



        TabLayout tabLayout=findViewById(R.id.tabLayout);
        TabLayout.Tab moviesTab=tabLayout.newTab();
        TabLayout.Tab seriesTab=tabLayout.newTab();
        moviesTab.setText("MOVIES");
        seriesTab.setText("TV SHOWS");



        //moviesTab.setCustomView(R.layout.tab_layout);
        //View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_layout,null);
        //TextView tv=view.findViewById(R.id.tab_layout_text_view);
        //tv.setText("TV SHOWS");
        //seriesTab.setCustomView(view);


        tabLayout.addTab(moviesTab,0);
        tabLayout.addTab(seriesTab,1);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=findViewById(R.id.viewPager);
        PagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),this);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public Fragment getFrag(int Position) {
        return null;
    }
}
