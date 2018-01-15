package com.example.tanvi.moviemania.Templates;

import java.util.ArrayList;

/**
 * Created by tanvi on 05-01-2018.
 */

public class MovieCompleteDetail {

   private boolean adult;
   private String backdrop_path;
   private ArrayList<Genre> genres;
   private int id;
   private String original_language;
   private String overview;
   private String homepage;
   private int runtime;
   private String tagline;
   private String status;

    public MovieCompleteDetail(boolean adult, String backdrop_path, ArrayList<Genre> genres, int id, String original_language, String overview, String homepage, int runtime, String tagline, String status) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genres = genres;
        this.id = id;
        this.original_language = original_language;
        this.overview = overview;
        this.homepage = homepage;
        this.runtime = runtime;
        this.tagline = tagline;
        this.status = status;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public String getStatus() {
        return status;
    }
}
