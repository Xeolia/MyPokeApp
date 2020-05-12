package com.example.ghibliapp.presentation.Vue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghibliapp.R;
import com.example.ghibliapp.presentation.Modele.Ghibli;
import com.example.ghibliapp.presentation.controlleur.MainControleur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainControleur controleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controleur = new MainControleur(
                this,
                new GsonBuilder()
                        .setLenient()
                        .create(),
                getSharedPreferences("Ghibli Application", Context.MODE_PRIVATE)
        );
        controleur.onStart();

    }


    public void showList(List<Ghibli> ghibliList) {
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

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }

}