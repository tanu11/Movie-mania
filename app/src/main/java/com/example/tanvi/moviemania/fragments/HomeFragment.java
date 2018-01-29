package com.example.tanvi.moviemania.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvi.moviemania.Adapters.MyPagerAdapter;
import com.example.tanvi.moviemania.R;

public class HomeFragment extends Fragment implements MyPagerAdapter.InitaliseFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabLayout=view.findViewById(R.id.tabLayout);
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
        final ViewPager viewPager=view.findViewById(R.id.viewPager);

        PagerAdapter adapter=new MyPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount(),this);
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



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onHomeFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Fragment getFrag(int Position) {
        switch (Position)
        {
            case 0:
                MoviesTab moviesTab=new MoviesTab();
                Log.i("Fragment home","new created movie");
                return moviesTab;


            case 1:
                MoviesTab moviesTab2=new MoviesTab();
                Log.i("Fragment home","new created tv");
                return moviesTab2;

            default:
                return null;


        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onHomeFragmentInteraction(Uri uri);
    }
}
