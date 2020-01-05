package com.androidcourse.myapplication.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val movieRepository = MovieRepository(application.applicationContext)

    val movies = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()

    fun getMovies(year: String) {
        movieRepository.getMovies(year).enqueue(object: Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                // get the results from the body
                val results = response.body()?.get("results")
                // pass the result to an array of Movie objects
                val tempMovies = GsonBuilder().create().fromJson(results,Array<Movie>::class.java).toList()

                // set the id
//                for ((num, movie) in tempMovies.withIndex()) {
//                    tempMovies[num].id = num+1
//                }
                // check if the response is succesful, else show the error
                if (response.isSuccessful) this@MainActivityViewModel.movies.value = tempMovies
                else error.value = "An error occured: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }
        })
    }


}