package com.example.pokemonapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();


    @GET("/api/v2/ability")
    Call<RestPokemonResponse> getAbility();
}
