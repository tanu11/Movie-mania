package com.example.tanvi.moviemania.Templates;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tanvi on 14-01-2018.
 */

public class Crew {
    private String department;
    private String name;
    private String profile_path;

    @SerializedName("id")
    private int person_id;

    public Crew(String department, String name, String profile_path, int person_id) {
        this.department = department;
        this.name = name;
        this.profile_path = profile_path;
        this.person_id = person_id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public int getPerson_id() {
        return person_id;
    }
}
