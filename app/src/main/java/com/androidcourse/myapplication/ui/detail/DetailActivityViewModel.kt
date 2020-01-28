package com.androidcourse.myapplication.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie

import kotlinx.coroutines.*


class
DetailActivityViewModel(application: Application): AndroidViewModel(application) {
    private val movieRepository = MovieRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val TAG = "DetailViewModel"


    fun deleteMovie(movie: Movie) {
        ioScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }

}