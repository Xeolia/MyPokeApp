package com.example.pokemonapp;

import java.util.List;

public class RestPokemonResponse {
    private int count;
    private String next;
    private List<Pokemon> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}
