package com.android.dev.pokecard.service;

import com.android.dev.pokecard.models.Exchange;
import com.android.dev.pokecard.models.Pokemon;
import com.android.dev.pokecard.models.facebook.User;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by paulg on 13/12/2017.
 */

public interface ServicePokemon {

    /**
     * GET
     * Récupération des pokemons du user
     * @param userId Identifiant du user
     */
    @GET("/boloss_api/web/index.php/user/{userId}/pokemonsName")
    Call<List<Pokemon>> getAllPokemonsIds(@Path("userId") String userId);

    /**
     * GET
     * Récupération de tout les pokemons
     */
    @GET("/boloss_api/web/index.php/getAllPokemon")
    Call<List<Pokemon>> getAllPokemons(); // AJOUTER WEB ????

    /**
     * GET
     * Récupération de tout les pokemons
     */
    @GET("/boloss_api/web/index.php/getUsersExchanges/{userId}")
    Call<List<Exchange>> getExchanges(@Path("userId") String userId);

    /**
     * GET
     * Récupération de tout les pokemons
     */
    @GET("/boloss_api/web/index.php/getMyExchanges/{userId}")
    Call<List<Exchange>> getMyExchanges(@Path("userId") String userId);

    /**
     * GET
     * Récupération du User
     * @param userId Identifiant du user
     */
    @GET("/boloss_api/web/index.php/getUser/{userId}")
    Call <User> getUserById(@Path("userId") String userId);

    /**
     * POST
     * Créer un user
     * @param json
     */
    @POST("/boloss_api/web/index.php/createUser")
    Call<Void> createUser(@Body JsonObject json);

    /**
     * POST
     * Créer un échange
     * @param json
     */
    @POST("/boloss_api/web/index.php/createExchange")
    Call<Void> createExchange(@Body JsonObject json);

}
