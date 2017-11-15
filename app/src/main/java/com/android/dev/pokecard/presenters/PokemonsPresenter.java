package com.android.dev.pokecard.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.dev.pokecard.views.viewsinterfaces.PokemonView;

/**
 * Created by iem on 07/11/2017.
 */

public class PokemonsPresenter {
    private final Context context;
    private final PokemonView pokemonView;


    public PokemonsPresenter(Context context, PokemonView pokemonView) {
        this.context = context;
        this.pokemonView = pokemonView;
    }
}
