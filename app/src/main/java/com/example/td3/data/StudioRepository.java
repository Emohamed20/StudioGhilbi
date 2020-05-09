package com.example.td3.data;

import android.content.SharedPreferences;

import com.example.td3.presentation.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudioRepository {

    private StudioApi studioApi;
    private SharedPreferences sharedPreferences;

    public StudioRepository(StudioApi studioApi, SharedPreferences sharedPreferences) {
        this.studioApi = studioApi;
        this.sharedPreferences = sharedPreferences;
    }

    public void filmList(final StudioCallback callback){
        studioApi.filmList().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful()){
                    List<Film> filmList = response.body();
                }else {
                    callback.onFailed();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                   callback.onFailed();
            }
        });
    }
}
