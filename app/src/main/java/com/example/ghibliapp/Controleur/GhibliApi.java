package com.example.ghibliapp.Controleur;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GhibliApi {
    @GET("/films")
    Call<ArrayList<Ghibli>> getGhibliResponse();

}
