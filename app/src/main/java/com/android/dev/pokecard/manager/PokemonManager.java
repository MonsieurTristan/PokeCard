package com.android.dev.pokecard.manager;


import android.util.Log;

import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.service.ServicePokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulg on 13/12/2017.
 */

public class PokemonManager {
    private static PokemonManager instance;

    private ServicePokemon service;
    private List<Pokemon> pokemon = new ArrayList<>();
    private List<Pokemon> pokemonNames = new ArrayList<>();
    public static PokemonManager getInstance() {
        if (instance == null) {
            instance = new PokemonManager();
            instance.service = createService(ServicePokemon.class);
        }
        return instance;
    }

    public ArrayList<Pokemon> callWS() {
        new MyTask().execute();


        Call<List<Pokemon>> call = service.getAllPokemonsIds();
        try {
            Response<List<Pokemon>> response = call.execute();
            if(response.isSuccessful()) {
                pokemon = response.body();
                Log.d("pokemon", pokemon.get(1).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<Pokemon>) pokemon;
    }

    public ArrayList<Pokemon> callStringWS() {
        new MyTask().execute();

        Call<List<Pokemon>> call2 = service.getNames();
        try {
            Response<List<Pokemon>> response = call2.execute();
            if(response.isSuccessful()) {
                pokemonNames = response.body();
                Log.d("pokemon", pokemonNames.get(1).getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<Pokemon>) pokemonNames;
    }

    static String baseUrl = "http://antoinecervo.com/paul_api/web/index.php/";
    private static Retrofit.Builder mBuilder =
            new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        // log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(logging);

        okBuilder.readTimeout(1, TimeUnit.MINUTES);

        OkHttpClient httpClient = okBuilder.build();

        Retrofit retrofit = mBuilder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

}
