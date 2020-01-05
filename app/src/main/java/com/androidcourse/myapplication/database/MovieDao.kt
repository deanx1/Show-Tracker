package com.androidcourse.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androidcourse.myapplication.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun getAllGames(): LiveData<List<Movie>>

    @Update
    suspend fun updateGame(movie: Movie)

    @Delete
    suspend fun deleteGame(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllGames()

}