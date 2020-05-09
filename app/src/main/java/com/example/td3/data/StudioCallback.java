package com.example.td3.data;

import com.example.td3.presentation.model.Film;

import java.util.List;

public interface StudioCallback {

     void onSucces(Film response);
     void onFailed();

}
