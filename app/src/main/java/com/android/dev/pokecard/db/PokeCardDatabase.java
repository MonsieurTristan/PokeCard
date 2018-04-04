package com.android.dev.pokecard.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.models.facebook.User;

/**
 * Created by paulg on 28/03/2018.
 */

@Database(entities = {User.class ,Pokemon.class},  version = 1)
public abstract class PokeCardDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract PokedexDao pokedexDao();
}
