package com.example.tanvi.moviemania.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tanvi.moviemania.Networking.ApiClient;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Genre;
import com.example.tanvi.moviemania.Templates.GenreCover;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayActivity extends AppCompatActivity {
    private final String TAG ="Genres";
    public static  String api_key ="cd458af3e465c915faedf516d4f513c0";
    private ArrayList<Genre> genreArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        getGenre();








    }

    public void getGenre()
    {
        Call<GenreCover> call= ApiClient.getInterface().getGenre(api_key);
        call.enqueue(new Callback<GenreCover>() {

            @Override
            public void onResponse(Call<GenreCover> call, Response<GenreCover> response) {
                GenreCover genreCover=response.body();
                 genreArrayList=genreCover.getGenreList();
                 Log.i(TAG, "onResponse: "+genreArrayList.get(0).getName());
                 Toast.makeText(DisplayActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GenreCover> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }


}
