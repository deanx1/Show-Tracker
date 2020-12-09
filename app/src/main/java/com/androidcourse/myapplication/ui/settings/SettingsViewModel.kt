package com.androidcourse.myapplication.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.model.MovieId
import com.androidcourse.myapplication.model.SearchResult
import com.androidcourse.myapplication.model.jsonToKotlin.details.MovieDetails
import com.androidcourse.myapplication.ui.main.MainActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SettingsViewModel"


//    private val ioScope = CoroutineScope(Dispatchers.IO)
//    private val movieRepository = MovieRepository(application.applicationContext)
//
//    val searchJson = MutableLiveData<JsonObject>()
//    val error = MutableLiveData<String>()
//
//
//    fun insertMovie(movie: Movie) {
//        ioScope.launch {
//            movieRepository.insertMovie(movie)
//        }
//    }
//
//    fun getSearch(searchTitle: String) {
//        movieRepository.getSearch(searchTitle).enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    // get the results from the body
//                    val results = response.body()?.get("results")
//
//                    // Return a list of the SearchResults
//                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
//                    val searchStr = searchResult.toString()
//
//                    // Get the ID with regex
//                    val pattern1 = Regex("[0-9]+")
//                    val matchResult = pattern1.find(searchStr)
//                    val id = matchResult?.value
//
//                    if (id != null) {
//                        val idInt = id!!.toInt()
//                        searchJson.value = response.body()
//                        getDetails(idInt)
//                    }
//
//                }
//                else error.value = "An error occurred: ${response.errorBody().toString()}"
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                error.value = t.message
//            }
//        })
//
//    }
//
//    //TODO fix getDetails
//    fun getDetails(id: Int) {
//        var movie: Movie
//        Log.e(TAG, "getDetails: Starting ")
//        movieRepository.getMovieDetails(id).enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    // get the results from the body
//                    val results = response.body()
//                    Log.e(TAG, "getDetails: " + results)
//
//                    val detailResult = GsonBuilder().create().fromJson(results,MovieDetails::class.java)
//
//                    insertMovie(detailResult.toMovie())
//                }
//                else error.value = "An error occurred: ${response.errorBody().toString()}"
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.e(TAG, "error ")
//                error.value = t.message
//            }
//        })
//    }
//
//    fun MovieDetails.toMovie() = Movie(
//        backdrop_image_path = backdrop_path,
//        poster_image_path = poster_path,
//        title = name,
//        release_date = first_air_date,
//        average_rating = vote_average,
//        overview = overview,
//        tmdb_id = id,
//        genre1 = genres.getOrNull(0)?.name,
//        genre2 = genres.getOrNull(1)?.name,
//        genre3 = genres.getOrNull(2)?.name,
//        status = status,
//        last_episode_to_air = last_episode_to_air?.toString(),
//        last_air_date = last_air_date,
//        next_episode_to_air = next_episode_to_air?.toString()
//    )
}