package com.example.ghibliapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghibliapp.presentation.Vue.MainActivity;

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

        TextView monTitre = (TextView) findViewById(R.id.monTitre);
        valueFilm = "Titre :" + getIntent().getStringExtra("Titre");
        monTitre.setText(valueFilm);

        TextView maDecription = (TextView) findViewById(R.id.maDecription);
        valueDescription = "Description :" + getIntent().getStringExtra("Description");
        maDecription.setText(valueDescription);

        TextView monRealisateur = (TextView) findViewById(R.id.monRealisateur);
        valueRealisateur = "Film director :" + getIntent().getStringExtra("FilmDirector");
        monRealisateur.setText(valueRealisateur);

        TextView maDate = (TextView) findViewById(R.id.maDate);
        valueDate = "Date :" + getIntent().getStringExtra("Date");
        maDate.setText(valueDate);

        ImageView image = (ImageView) findViewById(R.id.monImage);
        int id = getResources().getIdentifier(valueFilm.substring(7,valueFilm.length()).replace(" ", "_").replace("'","").toLowerCase() + "_poster" , "drawable", MySecondFragment.this.getPackageName());
        image.setImageResource(id);



        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MySecondFragment.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
