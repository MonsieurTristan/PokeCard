package com.android.dev.pokecard;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.dev.pokecard.db.PokeCardDatabase;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.facebook.User;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by paulg on 28/03/2018.
 */

public class PokeCardApplication extends Application {
    public static PokeCardApplication INSTANCE;

    private PokeCardDatabase mDataBase;

    private User mUser;

    public static PokeCardApplication get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        WSManager.getInstance();
        mDataBase = Room.inMemoryDatabaseBuilder(this, PokeCardDatabase.class).build();
        //final User user = new User("42", "PaulG");

        if(mUser == null) {
            new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mUser = mDataBase.userDao().getUser();
                return null;
            }
        }.execute();
        }
    }

    public PokeCardDatabase getmDataBase () {
        return mDataBase;
    }

    public void insertUserDB(final User user) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mDataBase.userDao().insert(user);
                mUser = user;
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {

            }
        }.execute();

    }

    public User getAppUser() {
        if(mUser == null) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    mUser = mDataBase.userDao().getUser();
                    return null;
                }
            }.execute();
        }
        return mUser;
    }

}
