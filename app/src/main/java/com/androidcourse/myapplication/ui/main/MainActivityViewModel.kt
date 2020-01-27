package com.androidcourse.myapplication.ui.main

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.model.jsonToKotlin.details.MovieDetails
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val movieRepository = MovieRepository(application.applicationContext)

    private val TAG = "MainViewModel"

    val movies = MutableLiveData<List<MovieDetails>>()

    var tempMovies: MutableList<MovieDetails> = mutableListOf()

    val error = MutableLiveData<String>()

//    var movieIds : IntArray = intArrayOf(82856, 44217, 1399)
    var movieIds : IntArray = intArrayOf()

    private fun getMoviesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val databaseMovies = withContext(Dispatchers.IO) {
                movieRepository.getMoviesFromDatabase()
            }
        }
    }

    fun getMovies() {

        //todo check of de ids hier wel gevuld zijn.
        CoroutineScope(Dispatchers.Main).launch {
            val databaseMovies = withContext(Dispatchers.IO) {
                movieRepository.getMoviesFromDatabase()
            }
            for (id in databaseMovies) {
                movieIds += id.tmdb_id
//                            id.tmdb_id
                Log.e(TAG, "testtest: " + id.tmdb_id)
            }
            Log.e(TAG, "movieIds: " + movieIds)
        }
        for (id in movieIds) {
            movieRepository.getMovieDetails(id).enqueue(object : Callback<JsonObject> {

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        // get the results from the body
                        val results = response.body()
                        Log.e(TAG, "getDetails: " + results)

                        // Return a list of the SearchResults
//                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
                        val detailResult = GsonBuilder().create().fromJson(results, MovieDetails::class.java)

//                    detailJson.value = response.body()
                        tempMovies.add(detailResult)

                        // TODO refresh

                    }
                    else error.value = "An error occurred: ${response.errorBody().toString()}"
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(TAG, "error ")
                    error.value = t.message
                }
            })
        }
        // Todo bij eerste klik is movies 2 leeg

        movies.value = tempMovies
        Log.e(TAG, "movies: " + movies)


    }

//    fun getMovies(year: String) {
//    fun getMovies(year: String) {
////        movieRepository.getMovies(year).enqueue(object: Callback<JsonObject> {
//        movieRepository.getMovies(year).enqueue(object: Callback<JsonObject> {
//
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                // get the results from the body
//                val results = response.body()?.get("results")
//                // pass the result to an array of Movie objects
//                val tempMovies = GsonBuilder().create().fromJson(results,Array<Movie>::class.java).toList()
//
//                // set the id
////                for ((num, movie) in tempMovies.withIndex()) {
////                    tempMovies[num].id = num+1
////                }
//                // check if the response is succesful, else show the error
//                if (response.isSuccessful) this@MainActivityViewModel.movies.value = tempMovies
//                else error.value = "An error occured: ${response.errorBody().toString()}"
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                error.value = t.message
//            }
//        })
//    }




}