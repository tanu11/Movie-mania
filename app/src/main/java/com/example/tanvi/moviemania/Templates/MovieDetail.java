package com.example.tanvi.moviemania.Templates;

import java.io.Serializable;

/**
 * Created by tanvi on 30-12-2017.
 */

public class MovieDetail implements Serializable {

    private String title,overview,original_language,release_date,backdrop_path,poster_path;
    private int vote_count,id;
    private float vote_average;


    boolean adult;

    public MovieDetail(String title, String overview, String original_language, String release_date, String backdrop_path, String poster_path, int vote_count, int id, float vote_average,boolean adult) {
        this.title = title;
        this.overview = overview;
        this.original_language = original_language;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.vote_count = vote_count;
        this.id = id;
        this.vote_average = vote_average;
        this.adult=adult;
    }

    public boolean getIsAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
