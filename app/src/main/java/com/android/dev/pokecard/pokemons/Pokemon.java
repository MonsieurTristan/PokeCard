package com.android.dev.pokecard.pokemons;

/**
 * Created by paulg on 14/11/2017.
 */

public class Pokemon {
    private String name;
    private String legend;
    private String photoId;

    Pokemon(String name, String legend, String photoId) {
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