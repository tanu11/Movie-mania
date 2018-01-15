package com.example.tanvi.moviemania.Templates;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tanvi on 14-01-2018.
 */

public class Credits {
    private int id;
    private ArrayList<Cast> cast;
    private ArrayList<Crew> crew;

    public int getId() {
        return id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public Credits(int id, ArrayList<Cast> cast, ArrayList<Crew> crew) {

        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }
}
