package com.android.dev.pokecard.models;

/**
 * Created by paulg on 14/11/2017.
 */

public class Pokemon {

    //@SerializedName("name")
    private String name;

    private String id;

    private boolean owned=false;

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public Pokemon(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        String photoUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.id +".png";
        return photoUrl;
    }
}