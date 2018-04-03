package com.android.dev.pokecard.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by paulg on 02/04/2018.
 */

@Entity(indices = {@Index(value = "id", unique = true)})
public class Exchange {

    @PrimaryKey @NonNull String id;

    @ColumnInfo(name = "iduser1")
    String iduser1;

    @ColumnInfo(name = "idpokemon1")
    String idpokemon1;

    @ColumnInfo(name = "idpokemon2")
    String idpokemon2;

    @ColumnInfo(name = "status")
    String status;

    public Exchange (String idExchange, String userId, String idPokemon1, String idPokemon2, String statusExchange) {
        id = idExchange;
        iduser1 = userId;
        idpokemon1 = idPokemon1;
        idpokemon2 = idPokemon2;
        status = statusExchange;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getIduser1() {
        return iduser1;
    }

    public void setIduser1(String iduser1) {
        this.iduser1 = iduser1;
    }

    public String getIdpokemon1() {
        return idpokemon1;
    }

    public void setIdpokemon1(String idpokemon1) {
        this.idpokemon1 = idpokemon1;
    }

    public String getIdpokemon2() {
        return idpokemon2;
    }

    public void setIdpokemon2(String idpokemon2) {
        this.idpokemon2 = idpokemon2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
