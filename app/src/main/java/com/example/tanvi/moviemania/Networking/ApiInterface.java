package com.example.tanvi.moviemania.Networking;

import com.example.tanvi.moviemania.Activites.DisplayActivity;
import com.example.tanvi.moviemania.Templates.Genre;
import com.example.tanvi.moviemania.Templates.GenreCover;
import com.example.tanvi.moviemania.Templates.MovieDetailCover;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tanvi on 28-12-2017.
 */

public interface ApiInterface {

    @GET("genre/movie/list")
    Call<GenreCover> getGenre(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MovieDetailCover> getPopularMovie(@Query("api_key") String api_key, @Query("page")int page);


}
