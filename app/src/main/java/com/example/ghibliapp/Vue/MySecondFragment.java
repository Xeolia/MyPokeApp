package com.example.ghibliapp.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghibliapp.R;

public class MySecondFragment extends AppCompatActivity {
    String valueDescription = "Hello world";
    String valueRealisateur = "Hello world";
    String valueDate = "Hello world";
    String valueFilm = "Hello world";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_fragment);
        Button one = (Button) findViewById(R.id.buttonRetour);
        // String sent=intent.getStringExtra("Decription");

        TextView monTitre = (TextView) findViewById(R.id.monTitre);
        valueFilm = "Titre :" + getIntent().getStringExtra("Titre");
        monTitre.setText(valueFilm);

        TextView maDecription = (TextView) findViewById(R.id.maDecription);
        valueDescription = "Description :" + getIntent().getStringExtra("Description");
        maDecription.setText(valueDescription);

        TextView monRealisateur = (TextView) findViewById(R.id.monRealisateur);
        valueRealisateur = "FilmDirector :" + getIntent().getStringExtra("FilmDirector");
        monRealisateur.setText(valueRealisateur);

        TextView maDate = (TextView) findViewById(R.id.maDate);
        valueDate = "Date :" + getIntent().getStringExtra("Date");
        maDate.setText(valueDate);

        //int id = getResources().getIdentifier(mo, "drawable", DetailActivity.this.getPackageName());
        //flag.setImageResource(id);

        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MySecondFragment.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
