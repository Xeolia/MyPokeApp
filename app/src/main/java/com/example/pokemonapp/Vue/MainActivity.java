package com.example.pokemonapp.Vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pokemonapp.Modele.ListAdapter;
import com.example.pokemonapp.Controleur.Pokemon;
import com.example.pokemonapp.R;
import com.example.pokemonapp.Controleur.PokeApi;
import com.example.pokemonapp.Controleur.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    private static final String BASE_URL ="https://ghibliapi.herokuapp.com";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Pokemon Application", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Pokemon> pokemonList = getDataFromCache();
        if(pokemonList !=null){
            showList(pokemonList);
        }
        else{
            makeApiCall();
        }
    }

    private List <Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString("jsonPokemonList", null);

        if(jsonPokemon==null){
            return null;
        }else {
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);
        }
    }


    private void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);
        Call<ArrayList<Pokemon>> call = pokeApi.getPokemonResponse();
        call.enqueue(new Callback<ArrayList<Pokemon>>(){
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response)
            {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Pokemon> pokemonList = response.body();
                    saveList(pokemonList);
                        showList(pokemonList);
                    Toast.makeText(getApplicationContext(),"API Success",Toast.LENGTH_LONG).show();
                } else {
                    showError();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();

        Toast.makeText(getApplicationContext(),"List saved",Toast.LENGTH_LONG).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API Error",Toast.LENGTH_LONG).show();
    }

}