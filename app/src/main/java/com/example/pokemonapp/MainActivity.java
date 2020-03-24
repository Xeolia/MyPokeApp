package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://pokeapi.co/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected Void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //showList();

        sharedPreferences = getSharedPreferences("Projet Pokemon", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Pokemon> pokemonList = getDataFromCache();
        if (pokemonList!=null)
            showList(pokemonList);
            else{

            makeApiCall();

        }

    private List<Pokemon> getDataFromCache() {
            String JsonPokemon = sharedPreferences.getString("jsonPokemonList", null);
            Type listType = new TypeToken<List<Pokemon>>() {
            }.getType();
            return gson.fromJson(JsonPokemon, listType);
        }

    }

    private List<Pokemon> getDataFromCache() {
    }

    private void showList(List<Pokemon> pokemonList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

        Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
        @Override
        public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
            if(response.isSuccessful() && response.body() != null){
                List<Pokemon> pokemonList = response.body().getResults();
                savedList(pokemonList);
                showList(pokemonList);
            }
        }

        @Override
        public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
            showError();
        }
    });

    }

    private void savedList(List<Pokemon> pokemonList) {
        String JsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("jsonPokemonList", "JsonString")
                .apply();
        Toast.makeText(getApplicationContext(), "List xSaved", Toast.LENGTH_SHORT).show();
    }

    private void showError(){
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

}
