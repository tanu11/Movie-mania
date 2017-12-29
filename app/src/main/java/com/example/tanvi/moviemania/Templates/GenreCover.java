package com.example.tanvi.moviemania.Templates;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tanvi on 28-12-2017.
 */

public class GenreCover {
   @SerializedName("genres")
    public ArrayList<Genre> GenreList;

    public ArrayList<Genre> getGenreList() {
        return GenreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        GenreList = genreList;
    }



}
