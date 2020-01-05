package com.androidcourse.myapplication.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.androidcourse.myapplication.api.MovieRepository
import com.androidcourse.myapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val movieRepository = MovieRepository(application.applicationContext)

    fun insertGame(movie: Movie) {
        ioScope.launch {
            movieRepository.insertMovie(movie)
            Log.e("AddViewModel", "inserting")
        }
    }

}