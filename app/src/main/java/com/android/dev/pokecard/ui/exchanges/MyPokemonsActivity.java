package com.android.dev.pokecard.ui.exchanges;

/**
 * Created by paulg on 31/01/2018.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import com.android.dev.pokecard.R;
import com.android.dev.pokecard.adapter.PokemonsAdapter;
import com.android.dev.pokecard.manager.MyPokemonManager;
import com.android.dev.pokecard.manager.WSManager;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.service.ServicePokemon;
import com.android.dev.pokecard.views.activity.BaseActivity;


public class MyPokemonsActivity extends BaseActivity implements PokemonsAdapter.OnItemListener{

    MyPokemonManager myPokemonManager = MyPokemonManager.getInstance();


    @BindView(R.id.rv) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);
        ButterKnife.bind(this);

         new Thread(() -> {
             //final List<Pokemon> pokemons = WSManager.getInstance().getPokemonByUserId();
             runOnUiThread(() -> afficherPokemons(myPokemonManager.getMypokemons()));
         }).start();

    }

    public void afficherPokemons(List<Pokemon> pokemons) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyPokemonsActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new PokemonsAdapter(MyPokemonsActivity.this, pokemons, this));
        Toast.makeText(this,"nombre de Pokemon : "+ pokemons.size(),Toast.LENGTH_SHORT).show();
    }


    /*private ServicePokemon initRetrofit() {
        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        //.baseUrl("https://pokeapi.co/api/v2/")
                        //.baseUrl("http://localhost:8888/")
                        .baseUrl("http://antoinecervo.com/boloss_api/web/index.php/")
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        //if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(logging);
        //}
        okBuilder.readTimeout(1, TimeUnit.MINUTES);
//          pokemons = PokeapiService.listPokemon(20, offset);
        OkHttpClient httpClient = okBuilder.build();
        Retrofit retrofit = mBuilder.client(httpClient).build();
        return retrofit.create(ServicePokemon.class);
    }*/

    @Override
    public void onClickItem(Pokemon pokemon) {
        startActivity(WantedExchangeActivity.newIntent(this, pokemon.getId()));
    }
}




