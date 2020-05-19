package com.example.ghibliapp.presentation.Vue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.Glide;
import com.example.ghibliapp.R;

import java.net.URL;

public class CustomProgressBarActivity extends AppCompatActivity {

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    Thread loading;
    private ImageView monGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progressbar);
        ImageView monGif = (ImageView) findViewById(R.id.imageView);
        Glide GlideApp;
        String url = "https://66.media.tumblr.com/tumblr_lpsdjmuPoX1qf94kbo1_500.gifv";

        Glide.with(getApplicationContext())
                .load(url)
                .into(monGif);
        loadImageByInternetUrl();

        loading = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        loading.start();
    }


    private void loadImageByInternetUrl() {
        //String internetUrl =  ;



    }
}