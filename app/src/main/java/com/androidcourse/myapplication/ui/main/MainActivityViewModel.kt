package com.androidcourse.myapplication.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.model.jsonToKotlin.details.MovieDetails
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val movieRepository = MovieRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    val count = 0
    private val TAG = "MainViewModel"

    private val mainActivity = MainActivity()

    val movies: LiveData<List<Movie>> = movieRepository.getMoviesFromDatabase()

    var tempMoviesDetails: MutableList<MovieDetails> = mutableListOf()
    var tempMovies: MutableList<Movie> = mutableListOf()

    val error = MutableLiveData<String>()

    var movieIds : IntArray = intArrayOf(82856, 44217, 1399)

    private fun getMoviesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val databaseMovies = withContext(Dispatchers.IO) {
//                movieRepository.getMoviesFromDatabase()
            }
//            for (id in databaseMovies) {
//                movieIds += id.tmdb_id
////                            id.tmdb_id
//                Log.e(TAG, "AAAAAAAAAAAA: " + id.tmdb_id)
//            }
            Log.e(TAG, "movieIds: " + movieIds)
        }
    }

    fun getMovies() {
        //Get movies from database
//        getMoviesFromDatabase()

//        for (id in movieIds) {
//            movieRepository.getMovieDetails(id).enqueue(object : Callback<JsonObject> {
//
//                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                    if (response.isSuccessful) {
//                        // get the results from the body
//                        val results = response.body()
//                        Log.e(TAG, "getDetails: " + results)
//
//                        // Return a list of the SearchResults
////                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
//                        val detailResult = GsonBuilder().create().fromJson(results, MovieDetails::class.java)
//
////                    detailJson.value = response.body()
//                        tempMoviesDetails.add(detailResult)
//
//                        // TODO refresh
//                    }
//                    else error.value = "An error occurred: ${response.errorBody().toString()}"
//                }
//
//                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                    Log.e(TAG, "error ")
//                    error.value = t.message
//                }
//            })
//        }
//
//        for ( movie in tempMoviesDetails) {
//            val temp = movie.toMovie()
//            tempMovies.add(temp)
//        }

//            movies.value = tempMovies
//            movies. = tempMovies
//            mainActivity.movies.addAll(tempMovies)
//            mainActivity.movieAdapter.notifyDataSetChanged()


    }

}