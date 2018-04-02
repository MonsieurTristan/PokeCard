package com.android.dev.pokecard.ui.pokedex;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokedexAdapter;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 01/04/2018.
 */

public class PokedexFragment extends BaseFragment {

    @BindView(R.id.pokedex)
    RecyclerView pokedexRecyclerView;
    MyPokemonManager myPokemonManager = MyPokemonManager.getInstance();


    @Override
    protected int currentLayout() {
        return R.layout.fragment_pokedex;
    }

    public static PokedexFragment newInstance() {
        return new PokedexFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(currentLayout(), container, false);
        //mProgress = view.findViewById(R.id.progressBar);
        ButterKnife.bind(this, view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Pokemon> pokemons = WSManager.getInstance().getAllPokemon();
                final List<Pokemon> mypokemons = WSManager.getInstance().getPokemonByUserId();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myPokemonManager.setMypokemons(mypokemons);
                        for (Pokemon pokemon: pokemons ) {
                            pokemon.setOwned(myPokemonManager.iHaveThisPokemon(pokemon.getId()));
                        }
                        initPokedexRecyclerView(pokemons);
                    }
                });
            }
        }).start();


        return view;
    }




    public void initPokedexRecyclerView(List<Pokemon> pokemons){

        pokedexRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),4);
        pokedexRecyclerView.setLayoutManager(layoutManager);

        PokedexAdapter pokedexAdapter = new PokedexAdapter(this.getContext(), pokemons);
        pokedexRecyclerView.setAdapter(pokedexAdapter);
    }
}
