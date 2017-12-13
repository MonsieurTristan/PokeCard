package com.android.dev.pokecard.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paulg on 14/11/2017.
 */

public class Pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    private String legend;
    private String photoId;

    public Pokemon(int id, String name, String legend, String photoId) {
        this.id = id;
        this.name = name;
        this.legend = legend;
        this.photoId = photoId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

}