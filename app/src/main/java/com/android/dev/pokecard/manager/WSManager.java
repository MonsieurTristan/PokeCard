package com.android.dev.pokecard.manager;

import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.service.ServicePokemon;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

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
    static String urlTestCreation = "http://172.20.10.3";
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

    public List<Pokemon> getAllPokemon() {
        List<Pokemon> pokemons = new ArrayList<>();

        Call<List<Pokemon>> call = service.getAllPokemonsIds();
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

    public void createUser () {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", "69696969696969696969696969");
        jsonObject.addProperty("name", "Koko");
        jsonObject.addProperty("firstname", "Tristan");

        Call call = service.createUser(jsonObject);

        try {
            call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
