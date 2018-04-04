package com.android.dev.pokecard.views.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.android.dev.pokecard.PokeCardApplication;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.facebook.User;
import com.android.dev.pokecard.ui.exchanges.ExchangesFragment;
import com.android.dev.pokecard.ui.pokedex.PokedexFragment;
import com.android.dev.pokecard.ui.pokemons.MyPokemonsFragment;
import com.android.dev.pokecard.ui.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 27/03/2018.
 */

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.nav_home)
    BottomNavigationView mBottomView;

    private PokedexFragment mPokedexFragment;
    private MyPokemonsFragment mMyPokemonsFragment;
    private ExchangesFragment mExchangesFragment;
    private ProfileFragment mProfileFragment;

    private User mUser;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mUser = PokeCardApplication.get().getAppUser();

        mPokedexFragment = PokedexFragment.newInstance();
        mMyPokemonsFragment = MyPokemonsFragment.newInstance();
        mExchangesFragment = ExchangesFragment.newInstance();
        mProfileFragment = ProfileFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container, mPokedexFragment)
                .add(R.id.frame_container, mMyPokemonsFragment)
                .add(R.id.frame_container, mExchangesFragment)
                .add(R.id.frame_container, mProfileFragment)
                .hide(mPokedexFragment)
                .hide(mExchangesFragment)
                .hide(mProfileFragment)
                .commit();

        mBottomView.setOnNavigationItemSelectedListener(this);

        if (isOnline()) {
            new Thread(() -> {
                //GET USER WS Check if null create else do nothing
                if (WSManager.getInstance().getUserById() == null) {
                    WSManager.getInstance().createUser(PokeCardApplication.get().getAppUser());
                }
            }).start();
        } else {
            Intent intent = new Intent(HomeActivity.this, RetryActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default: break;
            case R.id.btn_pokedex:
                showPokedex();
                break;
            case R.id.btn_my_pokemons:
                showMyPokemons();
                break;
            case R.id.btn_exchange:
                showExchanges();
                break;
            case R.id.btn_account:
                showProfile();
                break;
        }
        return true;
    }


    private void showMyPokemons() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .show(mMyPokemonsFragment)
                .hide(mPokedexFragment)
                .hide(mExchangesFragment)
                .hide(mProfileFragment)
                .commit();
    }

    private void showPokedex() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .show(mPokedexFragment)
                .hide(mMyPokemonsFragment)
                .hide(mExchangesFragment)
                .hide(mProfileFragment)
                .commit();
    }

    private void showExchanges() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .show(mExchangesFragment)
                .hide(mMyPokemonsFragment)
                .hide(mPokedexFragment)
                .hide(mProfileFragment)
                .commit();
    }

    private void showProfile() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .show(mProfileFragment)
                .hide(mMyPokemonsFragment)
                .hide(mPokedexFragment)
                .hide(mExchangesFragment)
                .commit();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        //finishAffinity();
    }
}
