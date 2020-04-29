package com.example.pokemonapp.Controleur;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
    @GET("/films")
    Call<ArrayList<Pokemon>> getPokemonResponse();

}
