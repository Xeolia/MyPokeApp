package com.example.ghibliapp.Vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ghibliapp.Controleur.Ghibli;
import com.example.ghibliapp.Controleur.GhibliApi;
import com.example.ghibliapp.Modele.ListAdapter;
import com.example.ghibliapp.R;
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

        sharedPreferences = getSharedPreferences("Ghibli Application", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Ghibli> ghibliList = getDataFromCache();
        if(ghibliList !=null){
            showList(ghibliList);
        }
        else{
            makeApiCall();
        }
    }

    private List <Ghibli> getDataFromCache() {
        String jsongGhibli = sharedPreferences.getString("jsonGhibliList", null);

        if(jsongGhibli==null){
            return null;
        }else {
            Type listType = new TypeToken<List<Ghibli>>(){}.getType();
            return gson.fromJson(jsongGhibli,listType);
        }
    }


    private void showList(List<Ghibli> ghibliList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(ghibliList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GhibliApi ghibliApi = retrofit.create(GhibliApi.class);
        Call<ArrayList<Ghibli>> call = ghibliApi.getGhibliResponse();
        call.enqueue(new Callback<ArrayList<Ghibli>>(){
            @Override
            public void onResponse(Call<ArrayList<Ghibli>> call, Response<ArrayList<Ghibli>> response)
            {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Ghibli> ghibliList = response.body();
                    saveList(ghibliList);
                        showList(ghibliList);
                    Toast.makeText(getApplicationContext(),"API Success",Toast.LENGTH_LONG).show();
                } else {
                    showError();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Ghibli>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Ghibli> ghibliList) {
        String jsonString = gson.toJson(ghibliList);
        sharedPreferences
                .edit()
                .putString("jsonGhibliList", jsonString)
                .apply();

        Toast.makeText(getApplicationContext(),"List saved",Toast.LENGTH_LONG).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API Error",Toast.LENGTH_LONG).show();
    }

}