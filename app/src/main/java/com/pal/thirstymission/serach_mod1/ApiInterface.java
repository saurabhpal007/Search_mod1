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
 /*   @GET("getfc.php")
    Call<List<Users>> getfc(
                    @Query("key0") String key0,
                    @Query("key2") String key2,
                     @Query("key3") String key3
    );*/
    @GET("lo.php")
    Call<List<Users>> lo(
            @Query("key") String key,
            @Query("key0") String key0,
            @Query("key2") String key2,
            @Query("key3") String key3
    );
    @GET("getcllg.php")
    Call<List<Colleges>> getcllg(

    );
    @GET("getbr.php")
    Call<List<Branches>> getbr(

    );
    @GET("getyr.php")
    Call<List<Years>> getyr(

    );


}