package com.example.tanvi.moviemania.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tanvi.moviemania.fragments.MoviesTab;

/**
 * Created by tanvi on 29-12-2017.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    private InitaliseFragmentInterface listener;


    public MyPagerAdapter(FragmentManager fm, int mNoOfTabs, InitaliseFragmentInterface listener) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
        this.listener=listener;
    }

    @Override
    public Fragment getItem(int position) {
        return listener.getFrag(position);
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

    public interface InitaliseFragmentInterface
    {
        Fragment getFrag(int Position);

    }
}
