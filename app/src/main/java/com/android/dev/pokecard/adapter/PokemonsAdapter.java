package com.android.dev.pokecard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 02/03/2018.
 */

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.PokemonHolder> {
    private static final String TAG = PokemonsAdapter.class.getSimpleName();

    private Context mContext;
    private List<Pokemon> mPokemons;
    private final OnItemListener mListener;


    public interface OnItemListener {
        void onClickItem(Pokemon pokemon);
    }

    public PokemonsAdapter(Context context, List<Pokemon> pokemons, OnItemListener listener) {
        mListener = listener;
        mContext = context;
        mPokemons = pokemons;
    }

    @Override
    public PokemonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pokemon_item, parent, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonHolder holder, int position) {
        holder.bind(mPokemons.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pokemon) ImageView mPokemonImageView;
        @BindView(R.id.txt_pokemon_name) TextView mPokemonNameView;


        private View mView;

        public PokemonHolder(View itemView) {
            super(itemView);
            mView = itemView;
            //imageView = (ImageView)itemView.findViewById(R.id.img_pokemon);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Pokemon pokemon, int position) {
            Picasso.with(mContext).load(pokemon.getPhotoUrl())
                    .into(mPokemonImageView);
            mPokemonNameView.setText(pokemon.getName());
            mView.setOnClickListener(v -> mListener.onClickItem(pokemon));
        }
    }
}
