package com.example.ghibliapp.presentation.controlleur;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ghibliapp.Constant;
import com.example.ghibliapp.data.GhibliApi;
import com.example.ghibliapp.presentation.Modele.Ghibli;
import com.example.ghibliapp.presentation.Vue.MainActivity;
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

public class MainControleur {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainControleur(MainActivity view, Gson gson, SharedPreferences sharedPreferences) {
        this.view =view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        List<Ghibli> ghibliList = getDataFromCache();
        if (ghibliList != null) {
            view.showList(ghibliList);
        } else {
            makeApiCall();
        }

    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GhibliApi ghibliApi = retrofit.create(GhibliApi.class);
        Call<ArrayList<Ghibli>> call = ghibliApi.getGhibliResponse();
        call.enqueue(new Callback<ArrayList<Ghibli>>() {
            @Override
            public void onResponse(Call<ArrayList<Ghibli>> call, Response<ArrayList<Ghibli>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Ghibli> ghibliList = response.body();
                    saveList(ghibliList);
                    view.showList(ghibliList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Ghibli>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Ghibli> ghibliList) {
        String jsonString = gson.toJson(ghibliList);
        sharedPreferences
                .edit()
                .putString("jsonGhibliList", jsonString)
                .apply();


    }

    private List<Ghibli> getDataFromCache() {
        String jsongGhibli = sharedPreferences.getString("jsonGhibliList", null);

        if (jsongGhibli == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Ghibli>>() {
            }.getType();
            return gson.fromJson(jsongGhibli, listType);
        }
    }

    public void onClick(Ghibli ghibli) {

    }

}
