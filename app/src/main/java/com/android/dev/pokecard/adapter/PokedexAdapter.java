package com.android.dev.pokecard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexHolder> {

    List<Pokemon> pokemons;
    Context context;
    private final OnClickListener listener;

    public PokedexAdapter(Context context, List<Pokemon> pokemon, OnClickListener listener) {
        this.pokemons = pokemon;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PokedexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.pokedex_cell, parent, false);
        return new PokedexAdapter.PokedexHolder(view);
    }





    @Override
    public void onBindViewHolder(PokedexHolder holder, int position) {

        holder.bind(this.pokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }



    public interface OnClickListener{
        public void displayPokemon(Pokemon pokemon);
    }


    public class PokedexHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pokemonImage)
        ImageView pokemonImageView;

        private View mView;

        public PokedexHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Pokemon pokemon) {
            mView.setOnClickListener(v -> listener.displayPokemon(pokemon));

            pokemonImageView.setColorFilter(null);
            Picasso.with(context).load(pokemon.getPhotoUrl())
                    .into(this.pokemonImageView);
            if(!(pokemon.isOwned())){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);

                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                pokemonImageView.setColorFilter(filter);
            }
        }
    }
}
