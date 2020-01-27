package com.androidcourse.myapplication.api

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.androidcourse.myapplication.database.MovieDao
import com.androidcourse.myapplication.database.ShowTrackerRoomDatabase
import com.androidcourse.myapplication.model.Movie

class MovieRepository(context: Context) {

    private val movieDao: MovieDao

    private val movieApi: MovieApiService = MovieApi.createApi()

    init {
        val database = ShowTrackerRoomDatabase.getDatabase(context)
        movieDao = database!!.movieDao()
    }

    fun getSearch(name: String) = movieApi.getMovieSearch(name)

    fun getMovieDetails(id: Int) = movieApi.getMovieDetails(id)

    suspend fun insertMovie(movie: Movie) {
        Log.e("MovieRepository", "inserting " + movie.tmdb_id)
        movieDao.insertMovie(movie)
    }


    suspend fun getMoviesFromDatabase(): List<Movie> {
        return movieDao.getAllMovies()
    }


}