package com.example.td3.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.td3.R;
import com.example.td3.presentation.Singletons;
import com.example.td3.presentation.model.Film;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    private TextView anDetail;
    private TextView desDetail;
    private TextView directDetail;
    private TextView producDetail;
    private TextView scoreDetail;
    private TextView urlDetail;
    private TextView idDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.derail_txt);//nom id de mon TextView
        anDetail = findViewById(R.id.detailAnnee);
        desDetail = findViewById(R.id.detailDescription);
        directDetail = findViewById(R.id.detailDirector);
        producDetail = findViewById(R.id.detailProducer);
        scoreDetail = findViewById(R.id.detailScore);
        urlDetail = findViewById(R.id.detailUrl);
        idDetail = findViewById(R.id.detailId);

        Intent intent = getIntent();
        String studioJson = intent.getStringExtra("StudioKeyTitre");
        Film film = Singletons.getGson().fromJson(studioJson, Film.class);
        showDetail(film);
    }

    private void showDetail(Film film) {
         txtDetail.setText(film.getTitre());
         anDetail.setText(film.getDate_sortie());
         desDetail.setText(film.getDescription());
         directDetail.setText(film.getDirecteur());
         producDetail.setText(film.getProdcuteur());
         scoreDetail.setText(film.getScore());
         urlDetail.setText(film.getUrl());
         idDetail.setText(film.getId());
    }

}
