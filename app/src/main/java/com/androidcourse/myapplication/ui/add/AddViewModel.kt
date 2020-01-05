package com.androidcourse.myapplication.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.androidcourse.myapplication.model.SearchResult
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
                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
//                    val searchResult = GsonBuilder().create().fromJson(results,Array<SearchResult>::class.java).toList()
                    Log.e(TAG, "search: " + searchResult.toString())
                    val id = searchResult
                    Log.e(TAG, "search: " + id.toString())
//                    Log.e(TAG, searchJson.toString())
                    searchJson.value = response.body()


//                    val result = searchJson.value?.get("results")
//                    Log.e(TAG, result.toString())
//                    val test = result?.asJsonArray
//                    val id = test?.get(1)
//                    Log.e(TAG, id.toString())
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}