package com.android.dev.pokecard.ui.pokedex;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.pokecard.BaseFragment;
import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokedexAdapter;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 01/04/2018.
 */

public class PokedexFragment extends BaseFragment implements PokedexAdapter.OnClickListener {

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

        /*new Thread(new Runnable() {
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
        }).start();*/


        return view;
    }




    public void initPokedexRecyclerView(List<Pokemon> pokemons) {

        pokedexRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),4);
        pokedexRecyclerView.setLayoutManager(layoutManager);

        PokedexAdapter pokedexAdapter = new PokedexAdapter(this.getContext(), pokemons, this);
        pokedexRecyclerView.setAdapter(pokedexAdapter);
    }

    @Override
    public void displayPokemon(Pokemon pokemon) {
        if(pokemon.isOwned()){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.getContext());
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_display_pokemon, null);
            dialogBuilder.setView(dialogView);


            TextView textView = dialogView.findViewById(R.id.name);
            textView.setText(textView.getText() +" "+ pokemon.getName());

            ImageView imageView =  dialogView.findViewById(R.id.pokemonImage);
            Picasso.with(this.getContext()).load(pokemon.getPhotoUrl())
                    .into(imageView);

            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();


        }else{

            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                //deprecated in API 26
                v.vibrate(500);
            }
            Context context = this.getContext();
            CharSequence text = "You must have this pokemon to view its details";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}
