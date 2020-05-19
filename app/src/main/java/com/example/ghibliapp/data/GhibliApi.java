package com.example.ghibliapp.data;

import com.example.ghibliapp.presentation.Modele.Ghibli;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GhibliApi {
    @GET("/films")
    Call<ArrayList<Ghibli>> getGhibliResponse();



}
