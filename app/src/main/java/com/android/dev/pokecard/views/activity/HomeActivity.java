package com.android.dev.pokecard.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.android.dev.pokecard.BaseActivity;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.ui.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 27/03/2018.
 */

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.nav_home)
    BottomNavigationView mBottomView;

    private ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mProfileFragment = ProfileFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container, mProfileFragment)
                .hide(mProfileFragment)
                .commit();

        mBottomView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default: break;
            case R.id.btn_account:
                showProfile();
                break;
            /*case R.id.btn_store_map:
                showMap();
                break;
            case R.id.btn_loyalty:
                showLoyalty();
                break;*/

        }
        return true;
    }

    private void showProfile() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                //.hide(mProfile)
                .show(mProfileFragment)
                .commit();
    }
}
