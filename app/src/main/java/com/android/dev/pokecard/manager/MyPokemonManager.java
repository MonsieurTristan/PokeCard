package com.android.dev.pokecard.manager;

import com.android.dev.pokecard.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MyPokemonManager {


    private static MyPokemonManager instance;
    private List<Pokemon> mypokemons = new ArrayList<Pokemon>();



    public static MyPokemonManager getInstance() {
        if (instance == null) {
            instance = new MyPokemonManager();
        }
        return instance;
    }

    public List<Pokemon> getMypokemons() {
        return mypokemons;
    }

    public void setMypokemons(List<Pokemon> mypokemons) {
        this.mypokemons = mypokemons;
    }

    public boolean iHaveThisPokemon(String id){
        for (Pokemon pokemon: mypokemons) {
            if(pokemon.getId().equals(id))
                return true;
        }
        return false;
    }
}
