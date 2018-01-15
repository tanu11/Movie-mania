package com.example.tanvi.moviemania.Templates;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tanvi on 30-12-2017.
 */

public class MovieDetailCover {

    @SerializedName("results")
    private ArrayList<MovieDetail> movieDetails;

    public ArrayList<MovieDetail> getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(ArrayList<MovieDetail> movieDetails) {
        this.movieDetails = movieDetails;
    }
}
