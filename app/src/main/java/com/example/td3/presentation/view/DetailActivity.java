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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.derail_txt);//nom de la zone ou afficher les donnes
        Intent intent = getIntent();
        String studioJson = intent.getStringExtra("StudioKeyTitre");
        Film film = Singletons.getGson().fromJson(studioJson, Film.class);
        showDetail(film);
    }

    private void showDetail(Film film) {
        txtDetail.setText(film.getTitre());
    }

}
