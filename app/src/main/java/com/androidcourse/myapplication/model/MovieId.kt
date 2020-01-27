package com.androidcourse.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class MovieId(
    @SerializedName("tmdb_id") var tmdb_id: Int,
    @PrimaryKey var id: Long? = null
//    var id: Int
): Parcelable {
//    fun getBackdrop() = "https://image.tmdb.org/t/p/w500/$backdrop_image_path"
//
//    fun getPoster() = "https://image.tmdb.org/t/p/w500/$poster_image_path"

}