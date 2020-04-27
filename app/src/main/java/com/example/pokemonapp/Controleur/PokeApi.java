package com.example.pokemonapp.Controleur;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
   // @GET("/api/v2/pokemon")
    @GET("/films")
    Call<RestPokemonResponse> getPokemonResponse();


   // @GET("/api/v2/ability")
    //Call<RestPokemonResponse> getAbility();
}
