package com.example.tanvi.moviemania.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.tanvi.moviemania.Adapters.GenreAdapter;
import com.example.tanvi.moviemania.Adapters.LessMovieDetailAdapter;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.MovieDetail;

import java.util.ArrayList;

public class MoreAboutActivity extends AppCompatActivity {
    private LessMovieDetailAdapter movieDetailAdapter;
    private RecyclerView movieRecyclerView;
    private RecyclerView.LayoutManager MovieLayoutManager;
    private ArrayList<MovieDetail> movieArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about);
        Toolbar toolbar=findViewById(R.id.moreAboutToolbar);

        toolbar.setTitle("Popular Movies");
        setSupportActionBar(toolbar);

        //setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));


        Bundle bundle=getIntent().getBundleExtra("bundle");
        movieArrayList= (ArrayList<MovieDetail>) bundle.getSerializable("MovieList");


        movieRecyclerView=findViewById(R.id.moreMovies);
        movieRecyclerView.setHasFixedSize(true);

        movieDetailAdapter=new LessMovieDetailAdapter(movieArrayList,2 );
        MovieLayoutManager =new GridLayoutManager(this,2);

        movieRecyclerView.setLayoutManager(MovieLayoutManager);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());

        movieRecyclerView.setAdapter(movieDetailAdapter);

    }
}
