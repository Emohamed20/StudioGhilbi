package com.example.td3.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.td3.presentation.Constants;
import com.example.td3.R;
import com.example.td3.data.StudioApi;
import com.example.td3.presentation.model.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapteur mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private static final String BASE_URL = "https://ghibliapi.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences =getSharedPreferences("application_ghilbi ", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();


    /*
        recyclerView = (RecyclerView) findViewById(R.id.recycler_VIEW);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        */

        List<Film> film = getDataFromCache();

        if(film != null){
            showList(film);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        StudioApi studioApi = retrofit.create(StudioApi.class);

        Call<List<Film>> call = studioApi.filmList();
        Log.d("VINCE", "BEFOR CALLBACK");
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                Log.d("VINCE", "INSIDE CALLBACK");
                if (response.isSuccessful()) {
                    List<Film> filmList = response.body();
                    saveList(filmList);
                    showList(filmList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                showError();
            }
        });
        Log.d("VINCE", "AFTER CALLBACK");
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }


    private void saveList(List<Film> filmList) {
        String jsonString=gson.toJson(filmList);//sauvegarder ma liste avec format json

        sharedPreferences
                .edit()
                .putString(Constants.KEY_FILM_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private List<Film> getDataFromCache() {
        String jsonFilm = sharedPreferences.getString(Constants.KEY_FILM_LIST, null);

        if(jsonFilm == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Film>>(){}.getType();
            return gson.fromJson(jsonFilm, listType);
        }
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

    private void showList(List<Film> filmList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_VIEW);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapteur(filmList);
        recyclerView.setAdapter(mAdapter);
    }


}
