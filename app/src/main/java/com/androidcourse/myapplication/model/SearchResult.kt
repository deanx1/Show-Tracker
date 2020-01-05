package com.androidcourse.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "result_table")
data class SearchResult(
    @SerializedName("id") var tmdb_id: Int
): Parcelable