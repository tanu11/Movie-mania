package com.example.tanvi.moviemania.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tanvi.moviemania.Adapters.LessMovieDetailAdapter;
import com.example.tanvi.moviemania.Networking.ApiClient;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Credits;
import com.example.tanvi.moviemania.Templates.GenreCover;
import com.example.tanvi.moviemania.Templates.MovieCompleteDetail;
import com.example.tanvi.moviemania.Templates.MovieDetail;
import com.example.tanvi.moviemania.Templates.MovieDetailCover;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tanvi.moviemania.Activites.MainActivity.api_key;

public class AboutAMovieActivity extends AppCompatActivity {
  private int movieId;
  private MovieCompleteDetail movieCompleteDetail;
  private ArrayList<MovieDetail> recommendedMovies;
  private ArrayList<MovieDetail> similarMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_amovie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);;


        Bundle Data=getIntent().getExtras();
        movieId=Data.getInt("movieId");

        Toast.makeText(this, "movieId"+movieId,Toast.LENGTH_SHORT).show();
        getMovieCompleteDetail();
        getMovieCredits();
        getRecommendations();
        getSimilarMovies();
    }

    private void getSimilarMovies() {
        Call<MovieDetailCover> call =ApiClient.getInterface().getSimilarMovies(movieId,api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {
            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {
                if(response.isSuccessful())
                {
                     similarMovies=response.body().getMovieDetails();
                }
                else
                    Toast.makeText(AboutAMovieActivity.this,"no result",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

            }
        });

    }

    private void getRecommendations() {
       Call<MovieDetailCover> call=ApiClient.getInterface().getRecommendedMovies(movieId,api_key,1);
       call.enqueue(new Callback<MovieDetailCover>() {
           @Override
           public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {
               recommendedMovies=response.body().getMovieDetails();

           }

           @Override
           public void onFailure(Call<MovieDetailCover> call, Throwable t) {

           }
       });
    }

    private void getMovieCompleteDetail() {
        Call<MovieCompleteDetail> call= ApiClient.getInterface().getMovieById(movieId,api_key);
        call.enqueue(new Callback<MovieCompleteDetail>() {
            @Override
            public void onResponse(Call<MovieCompleteDetail> call, Response<MovieCompleteDetail> response) {
                movieCompleteDetail= response.body();
                if(response.isSuccessful())
                 {
                    //Toast.makeText(AboutAMovieActivity.this,movieCompleteDetail.getTagline(),Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "onResponse: " + response.body().toString());
                }
                else
                    Toast.makeText(AboutAMovieActivity.this,"no result",Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<MovieCompleteDetail> call, Throwable t) {
                Toast.makeText(AboutAMovieActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();


            }
        });

     }

     private void getMovieCredits()
    {
        Call<Credits> call=ApiClient.getInterface().getMovieCredits(movieId,api_key);
        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                if(response.isSuccessful()){
                     Credits credits=response.body();
                     String name=credits.getCast().get(0).getName();
                    Toast.makeText(AboutAMovieActivity.this,name,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AboutAMovieActivity.this,"no results ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {

            }
        });

    }
}
