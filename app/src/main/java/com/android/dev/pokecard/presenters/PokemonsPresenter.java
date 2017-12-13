package com.android.dev.pokecard.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonRVAdapter;
import com.android.dev.pokecard.manager.PokemonManager;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.views.viewsinterfaces.PokemonView;

import java.util.ArrayList;

/**
 * Created by iem on 07/11/2017.
 */

public class PokemonsPresenter extends AsyncTask<Void, Integer, Void> {
    private final Context context;
    private final PokemonView pokemonView;
    private ArrayList<Pokemon> myList = new ArrayList<>();
    private PokemonRVAdapter rvAdapter;


    public PokemonsPresenter(Context context, PokemonView pokemonView) {
        this.context = context;
        this.pokemonView = pokemonView;
        initializeData();
    }


    public void initializeData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                myList =  PokemonManager.getInstance().callWS();
                rvAdapter.setPokemons(myList);
                pokemonView.refresh();
            }
        }).start();


        /*myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
    */
    }

    public PokemonRVAdapter getAdapter() {
        rvAdapter = new PokemonRVAdapter(myList);
        return rvAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        initializeData();
        return null;
    }
}
