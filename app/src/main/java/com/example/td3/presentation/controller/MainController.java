package com.example.td3.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.td3.data.StudioApi;
import com.example.td3.presentation.Constants;
import com.example.td3.presentation.model.Film;
import com.example.td3.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStard(){
        List<Film> film = getDataFromCache();

        if(film != null){
            view.showList(film);
        } else {
            makeApiCall();
        }
    }
    private void makeApiCall() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
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
                    view.showList(filmList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                view.showError();
            }
        });
        Log.d("VINCE", "AFTER CALLBACK");
    }

    private void saveList(List<Film> filmList) {
        String jsonString=gson.toJson(filmList);//sauvegarder ma liste avec format json

        sharedPreferences
                .edit()
                .putString(Constants.KEY_FILM_LIST, jsonString)
                .apply();
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

    public void onItemClick(Film film){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
