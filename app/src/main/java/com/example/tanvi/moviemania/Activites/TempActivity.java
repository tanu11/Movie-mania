package com.example.tanvi.moviemania.Activites;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.fragments.FavFragment;
import com.example.tanvi.moviemania.fragments.HomeFragment;
import com.example.tanvi.moviemania.fragments.UserInfoFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class TempActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,UserInfoFragment.OnFragmentInteractionListener,FavFragment.OnFragmentInteractionListener {

    public static  String api_key ="cd458af3e465c915faedf516d4f513c0";
    BottomNavigationView navigation;

    private ArrayList<Fragment> bottomBarList=new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0);


                    return true;
                case R.id.navigation_fav:
                switchFragment(1);


                    return true;
                case R.id.navigation_user_info:
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

//
//        BottomNavigationViewPager viewPager=findViewById(R.id.bottomBarViewpager);
//        viewPager.setPagingEnabled(false);
//        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),3,this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        addFragmentsInBottomBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        switchFragment(0);


    }

    private void addFragmentsInBottomBar() {
        bottomBarList.add(getFrag(0));
        bottomBarList.add(getFrag(1));
        bottomBarList.add(getFrag(2));
    }


    private void switchFragment(int pos) {

//        Log.i("Onswitchfragment","inside "+pos);
//        FragmentManager manager= getSupportFragmentManager();
//        Fragment fr=getSupportFragmentManager().findFragmentById(R.id.bottomNavFrameLayout);
//        if(fr!=null)
//            Log.i("fragment In", "switchFragment: "+fr.getClass());
//        else
//            Log.i("fragment In", "switchFragment: null");
//
//        FragmentTransaction transaction=manager.beginTransaction();
//
//        transaction.replace(R.id.bottomNavFrameLayout,bottomBarList.get(pos) ).addToBackStack(null);
//        transaction.commit();
        Fragment fragment=bottomBarList.get(pos);
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.bottomNavFrameLayout, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }


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


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() ==1){
          finish();
        }
        else {


            super.onBackPressed();
            Fragment fr=getSupportFragmentManager().findFragmentById(R.id.bottomNavFrameLayout);
            if(fr!=null) {
                Log.i("Curremt frag", "onBackPressed: " + fr.getClass());
                if(fr instanceof UserInfoFragment)
                { navigation.setSelectedItemId(R.id.navigation_user_info);

                }
                else if (fr instanceof FavFragment )
                {
                    navigation.setSelectedItemId(R.id.navigation_fav);
                }
                else
                {
                    navigation.setSelectedItemId(R.id.navigation_home);
                }


            }




        }
    }

}

