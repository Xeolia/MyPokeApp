package com.example.ghibliapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ghibliapp.data.GhibliApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static GhibliApi ghibliApiInstance;
    private static SharedPreferences sharedPreferences;


    public static Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static GhibliApi getGhibliApi() {
        if (ghibliApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            ghibliApiInstance = retrofit.create(GhibliApi.class);
        }
        return ghibliApiInstance;
    }
    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Ghibli Application", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

}