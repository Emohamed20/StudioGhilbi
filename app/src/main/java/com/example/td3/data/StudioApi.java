package com.example.td3.data;

import com.example.td3.presentation.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface StudioApi {
        @Headers({"Content-Type: application/json"})
        @GET("/films")
        Call<List<Film>> filmList();
}
