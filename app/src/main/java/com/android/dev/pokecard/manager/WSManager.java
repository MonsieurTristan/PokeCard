package com.android.dev.pokecard.manager;

import com.android.dev.pokecard.PokeCardApplication;
import com.android.dev.pokecard.models.Exchange;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.models.facebook.User;
import com.android.dev.pokecard.service.ServicePokemon;
import com.google.gson.JsonObject;

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
 * Created by paulg on 02/03/2018.
 */

public class WSManager {
    static String baseUrl = "http://antoinecervo.com/boloss_api/web/index.php/";
    //static String urlTestCreation = "http://192.168.44.3";
    static String urlTestCreation = "http://172.20.10.3";
    //static String urlTestCreation = "http://10.0.2.2";
    private static WSManager instance;
    private static ServicePokemon service;

    private static Retrofit.Builder mBuilder =
            new Retrofit.Builder()
                    .baseUrl(urlTestCreation)
                    .addConverterFactory(GsonConverterFactory.create());

    public static WSManager getInstance() {
        if (instance == null) {
            instance = new WSManager();
            service = createService(ServicePokemon.class);
        }
        return instance;
    }

    private static ServicePokemon createService(Class<ServicePokemon> serviceClass) {
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

    public List<Pokemon> getPokemonByUserId() {
        List<Pokemon> pokemons = new ArrayList<>();

        Call<List<Pokemon>> call = service.getAllPokemonsIds(PokeCardApplication.get().getmDataBase().userDao().getUser().getId());
        try {
            Response<List<Pokemon>> response = call.execute();
            if (response.isSuccessful()) {
                pokemons = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    public List<Pokemon> getAllPokemon() {
        List<Pokemon> pokemons = new ArrayList<>();

        Call<List<Pokemon>> call = service.getAllPokemons();
        try {
            Response<List<Pokemon>> response = call.execute();
            if (response.isSuccessful()) {
                pokemons = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    public void createUser (User user) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("name", user.getName());

        Call call = service.createUser(jsonObject);

        try {
            call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public User getUserById () {
        User user = new User();
        Call <User> call = service.getUserById(
                PokeCardApplication.get().getmDataBase().userDao().getUser().getId());
        try {
            Response <User> response = call.execute();
            if (response.isSuccessful()) {
                user = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void createExchange (String idPokemon1, String idPokemon2) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("iduser1", PokeCardApplication.get().getmDataBase().userDao().getUser().getId());
        jsonObject.addProperty("idpokemon1", idPokemon1);
        jsonObject.addProperty("idpokemon2", idPokemon2);

        Call call = service.createExchange(jsonObject);

        try {
            call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Exchange> getExchanges() {
        List<Exchange> exchanges = new ArrayList<>();

        Call<List<Exchange>> call = service.getExchanges(PokeCardApplication.get().getmDataBase().userDao().getUser().getId());
        try {
            Response<List<Exchange>> response = call.execute();
            if (response.isSuccessful()) {
                exchanges = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchanges;
    }

    public List<Exchange> getMyExchanges() {
        List<Exchange> exchanges = new ArrayList<>();

        Call<List<Exchange>> call = service.getMyExchanges(PokeCardApplication.get().getmDataBase().userDao().getUser().getId());
        try {
            Response<List<Exchange>> response = call.execute();
            if (response.isSuccessful()) {
                exchanges = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchanges;
    }
}

