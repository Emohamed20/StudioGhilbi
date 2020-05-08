package com.example.td3.presentation.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.td3.R;
import com.example.td3.presentation.Singletons;
import com.example.td3.presentation.controller.MainController;
import com.example.td3.presentation.model.Film;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapteur mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new MainController(
                     this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())

        );
        controller.onStard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showList(List<Film> filmList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_VIEW);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAdapter = new ListAdapteur(filmList, new ListAdapteur.OnItemClickListener() {
            @Override
            public void onItemClick(Film item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }


    public void navigateToDetails(Film film) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("StudioKeyTitre", Singletons.getGson().toJson(film));
        //myIntent.putExtra("StudioKeyDate", film.getDate_sortie());

        MainActivity.this.startActivity(myIntent);

    }
}
