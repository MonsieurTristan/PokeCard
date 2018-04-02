package com.android.dev.pokecard.service;

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
    @GET("/index.php/user/{userId}/pokemonsName")
    Call<List<Pokemon>> getAllPokemonsIds(@Path("userId") String userId);

    /**
     * GET
     * Récupération de tout les pokemons
     */
    @GET("/index.php/getAllPokemon")
    Call<List<Pokemon>> getAllPokemons();

    /**
     * GET
     * Récupération du User
     * @param userId Identifiant du user
     */
    @GET("/index.php/getUser/{userId}")
    Call <User> getUserById(@Path("userId") String userId);

    /**
     * POST
     * Permet de regénérer un mot de passe pour un client donnée.
     * Le client recevra un mail avec un lien de redéfinition de mot de passe.
     * @param json eMail du client
     */
    @POST("/index.php/createUser")
    Call<Void> createUser(@Body JsonObject json);
}
