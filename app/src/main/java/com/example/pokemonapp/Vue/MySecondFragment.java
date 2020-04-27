package com.example.pokemonapp.Vue;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemonapp.R;

public class MySecondFragment extends AppCompatActivity  {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second_fragment);
            Button one = (Button) findViewById(R.id.buttonRetour);

            one.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(MySecondFragment.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            
        }

}
