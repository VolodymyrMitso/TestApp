package com.mitso.testapp.api;

import com.mitso.testapp.models.json_entry_list.JsonEntryList;
import com.mitso.testapp.models.json_result.JsonResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("us/rss/topaudiobooks/limit=25/json")
    Call<JsonEntryList> getAudiobooks();

    @GET("us/rss/topmovies/limit=25/json")
    Call<JsonEntryList> getMovies();

    @GET("us/rss/toppodcasts/limit=25/json")
    Call<JsonEntryList> getPodcasts();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("lookup") // lookup?id=1
    Call<JsonResult> getResultById(@Query("id") String _entryId);
}