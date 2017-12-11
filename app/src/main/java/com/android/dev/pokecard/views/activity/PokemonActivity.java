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
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    private PokemonsPresenter pokemonsPresenter;

    private ArrayList<Pokemon> myList = new ArrayList<Pokemon>();

    private RecyclerView rv;
    private PokemonRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initDrawer();
        setContentView(R.layout.pokemon_activity);
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

    private void initDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_pokemon_list);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_friend_list);

        //create the drawer and remember the `Drawer` result object
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName(R.string.drawer_item_account)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position)
                        {
                            case 1:

                                Intent myIntent = new Intent(PokemonActivity.this, PokemonActivity.class);
                                PokemonActivity.this.startActivity(myIntent);
                        }
                        return true;
                    }
                })
                .build();
    }

    private void initializeInjection(){
        this.pokemonsPresenter = new PokemonsPresenter(this, this);
    }




}
