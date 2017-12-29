package com.example.tanvi.moviemania.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tanvi.moviemania.fragments.MoviesTab;

/**
 * Created by tanvi on 29-12-2017.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public MyPagerAdapter(FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                MoviesTab moviesTab=new MoviesTab();
                return moviesTab;

            case 1:
                MoviesTab moviesTab2=new MoviesTab();
                return moviesTab2;

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
