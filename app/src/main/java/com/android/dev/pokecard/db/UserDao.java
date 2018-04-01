package com.android.dev.pokecard.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.android.dev.pokecard.models.facebook.User;


/**
 * Created by paulg on 28/03/2018.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User WHERE id = :idFacebook")
    User getbyId(String idFacebook);

    @Query("SELECT * FROM User")
    User getUser();
}
