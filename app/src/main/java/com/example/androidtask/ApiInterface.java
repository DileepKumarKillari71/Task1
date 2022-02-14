package com.example.androidtask;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiInterface {
    // specify the sub url for our base url
    @GET("marvel-heroes")
    Call<ResponseData<List<Model>>> doGetListResources();
}
