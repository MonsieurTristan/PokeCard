package com.android.dev.pokecard;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.android.dev.pokecard.db.PokeCardDatabase;
import com.android.dev.pokecard.models.facebook.User;

/**
 * Created by paulg on 28/03/2018.
 */

public class PokeCardApplication extends Application {
    public static PokeCardApplication INSTANCE;

    private PokeCardDatabase mDataBase;

    public static PokeCardApplication get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDataBase = Room.inMemoryDatabaseBuilder(this, PokeCardDatabase.class).build();
        final User user = new User("42", "42", "42", "42", "42", "42");

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mDataBase.userDao().insert(user);

                return null;
            }
        }.execute();
        INSTANCE = this;
    }

    public PokeCardDatabase getmDataBase () {
        return mDataBase;
    }

}
