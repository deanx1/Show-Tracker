package com.androidcourse.myapplication.api

import android.content.Context
import android.util.Log
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

    fun getMovies(year: String) = movieApi.getMovies(year)

    fun getSearch(name: String) = movieApi.getMovieSearch(name)

    fun getMovieDetails(id: Int) = movieApi.getMovieDetails(id)

    suspend fun insertMovie(movie: Movie) {
        Log.e("GameRepository", "inserting")
        movieDao.insertMovie(movie)
    }
}