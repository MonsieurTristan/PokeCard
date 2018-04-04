package com.android.dev.pokecard.views.activity;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.dev.pokecard.db.PokeCardDatabase;

import butterknife.ButterKnife;

/**
 * Created by paulg on 27/03/2018.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
