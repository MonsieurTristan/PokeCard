package com.android.dev.pokecard.views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonRVAdapter;
import com.android.dev.pokecard.models.Pokemon;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iem on 07/11/2017.
 */

public class PokemonsActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private ArrayList<Pokemon> myList = new ArrayList<Pokemon>();
    private List<Pokemon> items;
    private RecyclerView rv;
    private PokemonRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);

        initializeData();
        rvAdapter = new PokemonRVAdapter(myList);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(PokemonsActivity.this));
        rv.setAdapter(rvAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

       /* private void initRecyclerView() {
            pokemonListAdapter = new PokemonListAdapter(getActivity(), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(pokemonListAdapter);
        }
*/
        rv.setAdapter(new PokemonRVAdapter(myList));

    }

    private void initializeData() {
        ImageView pikachuImageView = (ImageView) (findViewById(R.id.pokemon_photo));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));
        myList.add(new Pokemon("Pikachu", "Eclair", "https://i.pinimg.com/736x/76/47/9d/76479dd91dc55c2768ddccfc30a4fbf5--pikachu-halloween-costume-diy-halloween-costumes.jpg"));

        //items.add(new Pokemon("Germignon", "Plane", R.drawable.lion));
        //items.add(new Pokemon("Tiflosion", "Feu", R.drawable.handball));
    }

   /* private void initializeAdapter() {
        rvAdapter = new PokemonRVAdapter(items);
        rv.setAdapter(rvAdapter);
    }*/

}
