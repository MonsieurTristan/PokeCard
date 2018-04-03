package com.android.dev.pokecard.ui.exchanges;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonsAdapter;
import com.android.dev.pokecard.models.Exchange;
import com.android.dev.pokecard.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 02/04/2018.
 */

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeHolder>{

    private Context mContext;
    private List<Exchange> mExchanges;
    private final ExchangeAdapter.OnItemListener mListener;


    public interface OnItemListener {
        void onClickItem(Exchange exchange);
    }

    public ExchangeAdapter(Context context, List<Exchange> exchanges, OnItemListener listener) {
        mListener = listener;
        mContext = context;
        mExchanges = exchanges;
    }

    @Override
    public ExchangeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exchange_item, parent, false);
        return new ExchangeHolder(view);
    }

    @Override
    public void onBindViewHolder(ExchangeHolder holder, int position) {
        holder.bind(mExchanges.get(position), position);

    }

    @Override
    public int getItemCount() {
        return mExchanges.size();
    }

    public class ExchangeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_profile)
        ImageView mProfilView;

        @BindView(R.id.img_pokemon_wanted)
        ImageView mPokemonWantedView;

        @BindView(R.id.img_pokemon_donate)
        ImageView mPokemonDonateView;

        @BindView(R.id.btn_validate_exchange)
        Button mValidateExchangeButton;

        private View mView;

        public ExchangeHolder(View itemView) {
            super(itemView);
            mView = itemView;
            //imageView = (ImageView)itemView.findViewById(R.id.img_pokemon);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Exchange exchange, int position) {

            Picasso.with(mContext).load("https://graph.facebook.com/" + exchange.getIduser1() + "/picture?type=large")
                    .into(mProfilView);

            Picasso.with(mContext).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                    + exchange.getIdpokemon2() + ".png")
                    .into(mPokemonWantedView);

            Picasso.with(mContext).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                    + exchange.getIdpokemon1() + ".png")
                    .into(mPokemonDonateView);

            mValidateExchangeButton.setOnClickListener(v -> mListener.onClickItem(exchange));
        }
    }
}