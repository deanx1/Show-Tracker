package com.androidcourse.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class Movie(
    @SerializedName("backdrop_path") var backdrop_image_path: String,
    @SerializedName("poster_path") var poster_image_path: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var release_date: String,
    @SerializedName("vote_average") var average_rating: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("tmdb_id") var tmdb_id: Int,
    @PrimaryKey var id: Long? = null
//    var id: Int
): Parcelable {
    fun getBackdrop() = "https://image.tmdb.org/t/p/w500/$backdrop_image_path"

    fun getPoster() = "https://image.tmdb.org/t/p/w500/$poster_image_path"

}