package com.android.dev.pokecard.models.facebook;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by paulg on 28/03/2018.
 */

@Entity(indices = {@Index(value = "id", unique = true)})
public class User {

    @PrimaryKey @NonNull
    String id;

    @ColumnInfo(name = "name")
     String name;

    String pic_url = "https://graph.facebook.com/" + id + "/picture?type=large";

    public User() { }

    public User(String id, String name) {
        this.id = id;
        this.name = name;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
