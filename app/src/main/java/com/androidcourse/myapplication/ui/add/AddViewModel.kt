package com.androidcourse.myapplication.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.model.SearchResult
import com.androidcourse.myapplication.model.jsonToKotlin.MovieBig
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "AddViwModel"

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val movieRepository = MovieRepository(application.applicationContext)

    val searchJson = MutableLiveData<JsonObject>()
    val error = MutableLiveData<String>()

    fun insertGame(movie: Movie) {
        ioScope.launch {
            movieRepository.insertMovie(movie)
            Log.e(TAG, "inserting")
        }
    }

//    fun insertSearch(searchTitle: String) {
//        ioScope.launch {
//            movieRepository.getSearch(searchTitle)
//            Log.e("AddViewModel", "Searching")
//        }
//    }

    fun insertSearch(searchTitle: String) {
        movieRepository.getSearch(searchTitle).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    // get the results from the body
                    val results = response.body()?.get("results")
//                    Log.e(TAG, "search: " + results)

                    // Return a list of the SearchResults
//                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
                    val searchStr = searchResult.toString()

                    // Get the ID with regex
                    val pattern1 = Regex("[0-9]+")
                    val matchResult = pattern1.find(searchStr)
                    val id = matchResult?.value
                    val idInt = id!!.toInt()

                    insertDetails(idInt)
                    searchJson.value = response.body()
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }
        })
    }

    //TODO fix insertDetails
    fun insertDetails(id: Int) {
        movieRepository.getMovieDetails(id).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    // get the results from the body
                    val results = response.body()?.get("results")
//                    Log.e(TAG, "search: " + results)

                    // Return a list of the SearchResults
//                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
                    val searchResult = GsonBuilder().create().fromJson(results,Array<MovieBig>::class.java).toList()
                    val searchStr = searchResult.toString()

                    // Get the ID with regex
                    val pattern1 = Regex("[0-9]+")
                    val matchResult = pattern1.find(searchStr)
                    val id = matchResult?.value

                    searchJson.value = response.body()
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}