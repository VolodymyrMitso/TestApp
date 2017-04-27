package com.mitso.testapp.api;

import com.mitso.testapp.models.JsonData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("topaudiobooks/limit=25/json")
    Call<JsonData> getAudiobooks();

    @GET("topmovies/limit=25/json")
    Call<JsonData> getMovies();

    @GET("toppodcasts/limit=25/json")
    Call<JsonData> getPodcasts();
}