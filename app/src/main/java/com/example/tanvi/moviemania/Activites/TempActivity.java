package com.example.tanvi.moviemania.Activites;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.tanvi.moviemania.Adapters.MyPagerAdapter;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.fragments.FavFragment;
import com.example.tanvi.moviemania.fragments.HomeFragment;
import com.example.tanvi.moviemania.fragments.UserInfoFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class TempActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,UserInfoFragment.OnFragmentInteractionListener,FavFragment.OnFragmentInteractionListener {

    public static  String api_key ="cd458af3e465c915faedf516d4f513c0";

    private ArrayList<Fragment> bottomBarList=new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0);


                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(1);


                    return true;
                case R.id.navigation_notifications:
                   switchFragment(2);

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Fresco.initialize(this);
        Toolbar toolbar=findViewById(R.id.mainActivityToolbar);
        toolbar.setTitle("Movie Mania");
        setSupportActionBar(toolbar);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        addFragmentsInBottomBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(0);


    }

    private void addFragmentsInBottomBar() {
        bottomBarList.add(new HomeFragment());
        bottomBarList.add(new FavFragment());
        bottomBarList.add(new UserInfoFragment());
    }
    private void switchFragment(int pos) {

        Log.i("Onswitchfragment","inside "+pos);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.bottomNavFrameLayout, bottomBarList.get(pos));
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    @Override
    public void onFavFragmentInteraction(Uri uri) {

    }

    @Override
    public void onUserFragmentInteraction(Uri uri) {

    }

    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }


    public Fragment getFrag(int Position) {
        switch (Position)
        {
            case 0:
                HomeFragment homeFragment=new HomeFragment();
                Log.i("Fragment TA","new created home");
                return homeFragment;


            case 1:
                FavFragment favFragment=new FavFragment();
                Log.i("Fragment TA","new created fav");
                return favFragment;

            case 2:
                UserInfoFragment userInfoFragment=new UserInfoFragment();
                Log.i("Fragment TA","new created user");
                return userInfoFragment;

            default:
                return null;


        }
    }
}

