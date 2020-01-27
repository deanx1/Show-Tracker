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

//    val movies = MutableLiveData<List<MovieDetails>>()
    val movies = MutableLiveData<List<Movie>>()

    var tempMoviesDetails: MutableList<MovieDetails> = mutableListOf()
    var tempMovies: MutableList<Movie> = mutableListOf()

    val error = MutableLiveData<String>()

//    var movieIds : IntArray = intArrayOf(82856, 44217, 1399)
    private var movieIds : IntArray = intArrayOf()

    fun getMovies() {
        //Get movies from database
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
                        tempMoviesDetails.add(detailResult)

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

        // MovieDetails naar Movie

        for ( movie in tempMoviesDetails) {
            val temp = movie.toMovie()
            tempMovies.add(temp)
        }

        movies.value = tempMovies
        Log.e(TAG, "movies: " + movies)
    }

    fun MovieDetails.toMovie() = Movie(
        backdrop_image_path = backdrop_path,
        poster_image_path = poster_path,
        title = name,
        release_date = first_air_date,
        average_rating = vote_average,
        overview = overview,
        tmdb_id = id,
        genre1 = genres?.toString(),
        genre2 = genres?.toString(),
        genre3 = genres?.toString(),
        status = status,
        last_episode_to_air = last_episode_to_air?.toString(),
        last_air_date = last_air_date,
        next_episode_to_air = next_episode_to_air?.toString()
    )

}