package com.android.dev.pokecard.models.facebook;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by paulg on 28/03/2018.
 */

@Entity(indices = {@Index(value = "idFacebook", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)  int id;

    @ColumnInfo(name = "idFacebook")
     String idFacebook;
    @ColumnInfo(name = "gender")
     String gender;
    @ColumnInfo(name = "first_name")
     String first_name;
    @ColumnInfo(name = "last_name")
     String last_name;
    @ColumnInfo(name = "email")
     String email;
    @ColumnInfo(name = "birthdate")
     String birthdate;

    String pic_url = "https://graph.facebook.com/" + idFacebook + "/picture?type=large";

    public User() { }

    public User(String idFacebook, String gender, String first_name, String last_name, String email, String birthdate) {
        this.idFacebook = idFacebook;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
