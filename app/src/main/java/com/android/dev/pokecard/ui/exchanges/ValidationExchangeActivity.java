package com.android.dev.pokecard.ui.exchanges;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.android.dev.pokecard.R;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.utils.Constants;
import com.android.dev.pokecard.views.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by paulg on 02/04/2018.
 */

public class ValidationExchangeActivity extends BaseActivity {

    @BindView(R.id.img_pokemon_donate)
    ImageView mPokemonDonateView;

    @BindView(R.id.img_pokemon_wanted)
    ImageView mPokemonWantedView;

    @BindView(R.id.btn_validate_exchange_ask)
    Button mValidateExchangeButton;

    private String mPokemonDonateId;
    private String mPokemonWantedId;


    public static Intent newIntent(Context context, String pokemonDonateId, String pokemonWantedId) {
        Intent intent = new Intent(context, ValidationExchangeActivity.class);
        intent.putExtra(Constants.EXTRA_POKEMON_DONATE, pokemonDonateId);
        intent.putExtra(Constants.EXTRA_POKEMON_WANTED, pokemonWantedId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_exchange);
        ButterKnife.bind(this);

        mPokemonDonateId = getIntent().getStringExtra(Constants.EXTRA_POKEMON_DONATE);
        mPokemonWantedId = getIntent().getStringExtra(Constants.EXTRA_POKEMON_WANTED);

        Picasso.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                + mPokemonDonateId +".png")
                .into(mPokemonDonateView);

        Picasso.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                + mPokemonWantedId +".png")
                .into(mPokemonWantedView);

    }

    @OnClick(R.id.btn_validate_exchange_ask)
    public void onValidateExchange () {
        new Thread(() -> {
            WSManager.getInstance().createExchange(mPokemonDonateId, mPokemonWantedId);
            runOnUiThread(() -> finishAffinity());
        }).start();
    }

}
