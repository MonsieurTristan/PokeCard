package com.android.dev.pokecard.ui.exchanges;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokedexAdapter;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.utils.Constants;
import com.android.dev.pokecard.views.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 02/04/2018.
 */

public class WantedExchangeActivity extends BaseActivity implements PokedexAdapter.OnExchangeListener{

    public static Intent newIntent(Context context, String pokemonId) {
        Intent intent = new Intent(context, WantedExchangeActivity.class);
        intent.putExtra(Constants.EXTRA_POKEMON_DONATE, pokemonId);
        return intent;
    }

    @BindView(R.id.pokedex)
    RecyclerView pokedexRecyclerView;
    MyPokemonManager myPokemonManager = MyPokemonManager.getInstance();

    private String mPokemonDonateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_exchange);
        ButterKnife.bind(this);

        mPokemonDonateId = getIntent().getStringExtra(Constants.EXTRA_POKEMON_DONATE);

        new Thread(() -> {
            final List<Pokemon> pokemons = WSManager.getInstance().getAllPokemon();

            runOnUiThread(() -> {
                for (Pokemon pokemon : pokemons) {
                    pokemon.setOwned(myPokemonManager.iHaveThisPokemon(pokemon.getId()));
                }
                initPokedexRecyclerView(pokemons);
            });
        }).start();
    }

    public void initPokedexRecyclerView(List<Pokemon> pokemons) {

        pokedexRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        pokedexRecyclerView.setLayoutManager(layoutManager);

        PokedexAdapter pokedexAdapter = new PokedexAdapter(WantedExchangeActivity.this, pokemons, this);
        pokedexRecyclerView.setAdapter(pokedexAdapter);
    }


    @Override
    public void onItemSelectedForExchange(Pokemon pokemon) {
        startActivity(ValidationExchangeActivity.newIntent(WantedExchangeActivity.this, mPokemonDonateId ,pokemon.getId()));
    }
}
