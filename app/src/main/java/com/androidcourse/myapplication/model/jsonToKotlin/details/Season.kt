package com.androidcourse.myapplication.model.jsonToKotlin.details

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: Any,
    val poster_path: String,
    val season_number: Int
)