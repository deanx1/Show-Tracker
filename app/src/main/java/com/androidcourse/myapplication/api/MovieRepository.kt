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

    private var movieIds : IntArray = intArrayOf(82856, 44217, 1399)
    private var movies : IntArray = intArrayOf(82856, 44217, 1399)

    var movies2 = listOf<Movie>()


    init {
        val database = ShowTrackerRoomDatabase.getDatabase(context)
        movieDao = database!!.movieDao()
    }

//    fun getMovies() {
//        for (id in movieIds) {
//            val temp = movieApi.getMovieDetails(3)
//        }
//
//    }

//    fun getMovies() = movieApi.getMovieDetails(3)
    fun getMovies(year: String) = movieApi.getMovies(year)
//    fun getMovies() = movieDao.getAllMovies()

    fun getSearch(name: String) = movieApi.getMovieSearch(name)

    fun getMovieDetails(id: Int) = movieApi.getMovieDetails(id)

    suspend fun insertMovie(movie: Movie) {
        Log.e("MovieRepository", "inserting " + movie.tmdb_id)
        movieDao.insertMovie(movie)
    }

//    fun getMoviesFromDatabase() = movieDao.getAllMovies()

//    fun getMoviesFromDatabase(): List<Movie> {
//        return movieDao.getAllMovies()
//}

//    fun getMoviesFromDatabase() = movieDao.getAllMovies()
//    fun getMoviesFromDatabase() = movieDao.getAllMovies()
//      fun getMoviesFromDatabase() = movieDao.getAllMovies()

    suspend fun getMoviesFromDatabase(): List<Movie> {
        return movieDao.getAllMovies()
    }


//    fun getMoviesFromDatabase(): LiveData<List<Movie>> {
//        return movieDao.getAllMovies()
//    }

}