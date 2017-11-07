package com.android.dev.pokecard.pokemons;

import android.support.annotation.NonNull;

/**
 * Created by iem on 07/11/2017.
 */

public class PokemonsPresenter implements PokemonsContract.Presenter {
    private final PokemonsContract.View mPokemonsView;

    public PokemonsPresenter(@NonNull PokemonsContract.View mPokemonsView) {

        this.mPokemonsView = mPokemonsView;
    }

    @Override
    public void start() {

    }
}
