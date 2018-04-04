package com.android.dev.pokecard.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by paulg on 14/11/2017.
 */

@Entity
public class Pokemon {

    @PrimaryKey  @NonNull
    private String id;

    @ColumnInfo(name = "name")
    private String name;

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