package com.android.dev.pokecard.service;

import com.android.dev.pokecard.models.Pokemon;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by paulg on 13/12/2017.
 */

public interface ServicePokemon {
    /**
     * GET
     * Permet de vérifier si un email est disponible pour une création de compte. retourne vrai si
     * l'email est disponible pour une création de compte, faux sinon.
     * @param name  Partner Token Access
     * @return
     */


    @GET("http://localhost/API_PokeCard/web/index.php/user/1/pokemons")
    Call<Pokemon> getPokemonById(@Field("name")String name);

    @GET("http://localhost/API_PokeCard/web/index.php/user/1/pokemons")
    Call<Pokemon> getPokemon();

    @GET("user/1/pokemonsName")
    Call<List<Pokemon>> getAllPokemonsIds();

    @GET("user/1/pokemons")
    Call<List<Pokemon>> getNames();

    /**
     * POST
     * Permet de regénérer un mot de passe pour un client donnée.
     * Le client recevra un mail avec un lien de redéfinition de mot de passe.
     * @param json eMail du client
     */
    @POST("/index.php/createUser")
    Call<Void> createUser(@Body JsonObject json);
}
