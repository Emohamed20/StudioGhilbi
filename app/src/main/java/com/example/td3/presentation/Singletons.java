package com.example.td3.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.td3.data.StudioApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static StudioApi studioApiInstance;
    private static SharedPreferences sharedPreferenceInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static StudioApi getStudioApi(){
        if (studioApiInstance == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(client)
                    .build();

            studioApiInstance = retrofit.create(StudioApi.class);
        }
        return studioApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferenceInstance == null){
            sharedPreferenceInstance = context.getSharedPreferences("application_ghilbi ", Context.MODE_PRIVATE);
        }
        return sharedPreferenceInstance;
    }
}















