package com.example.tanvi.moviemania.Activites;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tanvi.moviemania.Adapters.GenreAdapter;
import com.example.tanvi.moviemania.Adapters.LessMovieDetailAdapter;
import com.example.tanvi.moviemania.Networking.ApiClient;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.MovieDetail;
import com.example.tanvi.moviemania.Templates.MovieDetailCover;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tanvi.moviemania.Activites.MainActivity.api_key;

public class MoreAboutActivity extends AppCompatActivity {
    private LessMovieDetailAdapter movieDetailAdapter;
    private RecyclerView movieRecyclerView;
    private int pageNo=1;
    private   int movieType;
    private RecyclerView.LayoutManager MovieLayoutManager;
    private ArrayList<MovieDetail> movieArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about);
        Toolbar toolbar=findViewById(R.id.moreAboutToolbar);
       // toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));




        Bundle bundle=getIntent().getBundleExtra("bundle");

        movieType=bundle.getInt("movieType");
        movieArrayList= (ArrayList<MovieDetail>) bundle.getSerializable("MovieList");
        //Log.i("movieType", "onCreate: "+movieType);
        //Toast.makeText(MoreAboutActivity.this,"content " + movieType,Toast.LENGTH_LONG).show();


        final SwipyRefreshLayout swipeToRefresh= findViewById(R.id.moreAboutSwipeRefresh);
        swipeToRefresh.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
        String str="Movies";
        switch (movieType)
        {
            case 1: str="Popular Movies";
                    break;
            case 2:str="Upcoming Movies";
                   break;
            case 3: str="Top Rated Movies";
                   break;
            case 4:str="Now Playing";
                   break;
        }
        toolbar.setTitle(str);
        setSupportActionBar(toolbar);


        movieRecyclerView=findViewById(R.id.moreMovies);
        movieRecyclerView.setHasFixedSize(true);

        movieDetailAdapter=new LessMovieDetailAdapter(movieArrayList,2 );
        MovieLayoutManager =new GridLayoutManager(this,2);

        movieRecyclerView.setLayoutManager(MovieLayoutManager);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());

        movieRecyclerView.setAdapter(movieDetailAdapter);

        movieRecyclerView.addOnItemTouchListener(new LessMovieDetailAdapter.RecyclerTouchListener(this,movieRecyclerView, new LessMovieDetailAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieDetail movie = movieArrayList.get(position);
                Toast.makeText(MoreAboutActivity.this, movie.getTitle()+" is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MoreAboutActivity.this, AboutAMovieActivity.class);
                i.putExtra("MovieDetail",movie);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeToRefresh.setDistanceToTriggerSync(100);



        swipeToRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                 swipeToRefresh.setRefreshing(true);
                 loadData();
                 swipeToRefresh.setRefreshing(false);
            }
        });



    }

    public void loadData()
    {
      getPopularMovie();
    }

    public void getPopularMovie()
    {


        Call<MovieDetailCover> call= ApiClient.getInterface().getPopularMovie(api_key,++pageNo);
        call.enqueue(new Callback<MovieDetailCover>() {

            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {

                MovieDetailCover movieDetailCover=response.body();
                int pos=movieArrayList.size();
                movieArrayList.addAll(movieDetailCover.getMovieDetails());
//                movieDetailAdapter=new LessMovieDetailAdapter(popularMovieArrayList,1);
//                movieRecyclerView.setAdapter(movieDetailAdapter);

                movieDetailAdapter.notifyItemRangeInserted(pos,movieArrayList.size()-pos);





                //Log.i(TAG, "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                //Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}
