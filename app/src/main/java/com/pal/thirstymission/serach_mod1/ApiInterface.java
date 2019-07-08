package com.pal.thirstymission.serach_mod1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getUs.php")
    Call<List<Users>> getUs(
            @Query("key") String key
    );
}