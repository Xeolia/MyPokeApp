package com.example.pokemonapp;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MySecondFragment extends AppCompatActivity  {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second_fragment);
            Button one = (Button) findViewById(R.id.buttonRetour);
            
        }

}
