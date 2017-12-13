package com.android.dev.pokecard.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonRVAdapter;
import com.android.dev.pokecard.drawer.NavigationDrawerImpl;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.presenters.PokemonsPresenter;
import com.android.dev.pokecard.views.viewsinterfaces.PokemonView;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;


/**
 * Created by iem on 07/11/2017.
 */

public class PokemonActivity extends AppCompatActivity implements PokemonView{
    private Drawer drawer;
    private Toolbar toolbar;

    private PokemonsPresenter pokemonsPresenter;

    private ArrayList<Pokemon> myList = new ArrayList<Pokemon>();

    private RecyclerView rv;
    private PokemonRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);
        this.drawer = new NavigationDrawerImpl(this).createNavigationDrawer(this);
        initializeInjection();
        initViews();

    }


    private void initViews(){
        //Init RecyclerView
        rvAdapter = pokemonsPresenter.getAdapter();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(PokemonActivity.this));
        rv.setAdapter(rvAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(rvAdapter);

        //Init ImageView
        ImageView pikachuImageView = (ImageView) (findViewById(R.id.pokemon_photo));
    }

    private void initializeInjection(){
        this.pokemonsPresenter = new PokemonsPresenter(this, this);
    }


    @Override
    public void refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rv.getAdapter().notifyDataSetChanged();
            }
        });
    }
}
