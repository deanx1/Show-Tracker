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
    @SerializedName("genre1") var genre1: String?,
    @SerializedName("genre2") var genre2: String?,
    @SerializedName("genre3") var genre3: String?,
    @SerializedName("status") var status: String?,
    @SerializedName("last_episode_to_air") var last_episode_to_air: String?,
    @SerializedName("last_air_date") var last_air_date: String?,
    @SerializedName("next_episode_to_air") var next_episode_to_air: String?,
    @PrimaryKey var id: Long? = null
//    var id: Int
): Parcelable {
    fun getBackdrop() = "https://image.tmdb.org/t/p/w500/$backdrop_image_path"

    fun getPoster() = "https://image.tmdb.org/t/p/w500/$poster_image_path"

}