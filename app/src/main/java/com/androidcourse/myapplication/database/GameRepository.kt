package com.androidcourse.myapplication.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.androidcourse.myapplication.model.Movie

class GameRepository(context: Context) {

    private val movieDao: MovieDao

    init {
        val database = ShowTrackerRoomDatabase.getDatabase(context)
        movieDao = database!!.movieDao()
    }

    suspend fun updateNotepad(game: Movie) {
        movieDao.updateGame(game)
    }

    fun getAllGames(): LiveData<List<Movie>> {
        return movieDao.getAllGames()
    }

    suspend fun insertGame(movie: Movie) {
        Log.e("GameRepository", "inserting")
        movieDao.insertMovie(movie)
    }

    suspend fun deleteAllGames() = movieDao.deleteAllGames()

    suspend fun deleteGame(game: Movie) =movieDao.deleteGame(game)

}