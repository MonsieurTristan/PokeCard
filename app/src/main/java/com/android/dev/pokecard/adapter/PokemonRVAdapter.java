package com.android.dev.pokecard.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.dev.pokecard.R;
import com.android.dev.pokecard.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by paulg on 14/11/2017.
 */

public class PokemonRVAdapter extends RecyclerView.Adapter<PokemonRVAdapter.PokemonViewHolder> {
    static Context context;
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView pokemonName;
        TextView pokemonType;
        ImageView pokemonPhoto;

        PokemonViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView) itemView.findViewById(R.id.cv);
            pokemonName = (TextView) itemView.findViewById(R.id.pokemon_name);
            pokemonType = (TextView) itemView.findViewById(R.id.pokemon_type);
            pokemonPhoto = (ImageView) itemView.findViewById(R.id.pokemon_photo);
        }
    }

    ArrayList<Pokemon> pokemons;

    public PokemonRVAdapter(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_layout, viewGroup, false);
        PokemonViewHolder pvh = new PokemonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder pokemonViewHolder, int i) {
        pokemonViewHolder.pokemonName.setText(pokemons.get(i).getName());
        pokemonViewHolder.pokemonType.setText(pokemons.get(i).getLegend());
        //pokemonViewHolder.pokemonPhoto.setImageResource(pokemons.get(i).getPhotoId());
        Picasso.with(context).load(pokemons.get(i).getPhotoId()).into(pokemonViewHolder.pokemonPhoto);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void addItem() {
        //pokemons.add(new Pokemon("Johann Zarco", "26 ans", R.drawable.zarco));
        notifyDataSetChanged();
    }

    public void deleteItem() {
        if (!pokemons.isEmpty()) {
            pokemons.remove(0);
            notifyDataSetChanged();
        }

    }

}
