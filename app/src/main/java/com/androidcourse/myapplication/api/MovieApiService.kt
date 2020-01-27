package com.androidcourse.myapplication.api

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface MovieApiService {

//    // Get method for the get request
//    @GET("/3/discover/movie?" +
//            "api_key=cd5a887a914e81181124a538a2908d2e" +
//            "&language=en-US" +
//            "&sort_by=popularity.desc" +
//            "&include_adult=true" +
//            "&include_video=false" +
//            "&page=1")
//    fun getMovies(@Query("year") year: String): Call<JsonObject>

    // Get method for the get request
    @GET("/3/discover/movie?" +
            "api_key=cd5a887a914e81181124a538a2908d2e" +
            "&language=en-US" +
            "&sort_by=popularity.desc" +
            "&include_adult=true" +
            "&include_video=false" +
            "&page=1")
    fun getMovies(@Query("year") year: String): Call<JsonObject>


    // Get method for the get request
    @GET("/3/search/tv?" +
            "api_key=cd5a887a914e81181124a538a2908d2e" +
            "&language=en-US" +
            "&page=1")
    fun getMovieSearch(@Query("query") name: String): Call<JsonObject>


    // Get method for the get request
    @GET("/3/tv/{tv_id}?" +
            "api_key=cd5a887a914e81181124a538a2908d2e" +
            "&language=en-US")
    fun getMovieDetails(@Path("tv_id") id: Int): Call<JsonObject>

//    // Get method for the get request
//    @GET("/3/discover/movie?" +
//            "api_key=cd5a887a914e81181124a538a2908d2e" +
//            "&language=en-US" +
//            "&sort_by=popularity.desc" +
//            "&include_adult=true" +
//            "&include_video=false" +
//            "&page=1")
//    fun getMovies(@Query("year") year: String): Call<JsonObject>


}