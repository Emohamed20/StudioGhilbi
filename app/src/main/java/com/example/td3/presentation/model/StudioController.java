package com.example.td3.presentation.model;

import android.util.Log;

import com.example.td3.data.StudioApi;
import com.example.td3.presentation.model.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudioController {
        static final String BASE_URL = "https://ghibliapi.herokuapp.com";


        public void start() {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

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
            System.out.println("Je suis là : ");
            Log.d("VINCE", "BEFOR CALLBACK");
            call.enqueue(new Callback<List<Film>>() {
                @Override
                public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                    Log.d("VINCE", "INSIDE CALLBACK");
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx : " + response.code() );
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx : " + response.body().size());
                    if (response.isSuccessful()) {
                        List<Film> filmList = response.body();
                        System.out.println("filmList : " + response.body());
                    } else {
                        System.out.println("fffffffffffffffffffffff : " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Film>> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println("ureuyrueyrueyruyeure : ");
                }
            });
            System.out.println("yyyyyyyyyyyyyyyyyyyyy : ");
            Log.d("VINCE", "AFTER CALLBACK");
        }
      /*  @Override
        public void onResponse (Call<List<Film>> call, Response<List<Film>> response){
            if (response.isSuccessful()) {
                List<Film> filmList = response.body();
            } else {
                System.out.println("Je suis là : " + response.errorBody());
            }
        }
        @Override
        public void onFailure (Call < List < Film >> call, Throwable t){
            t.printStackTrace();
            System.out.println("ureuyrueyrueyruyeure : ");
        }*/



}
