package com.example.mymovielist.api

import com.squareup.moshi.Json

data class NetworkMovie(
    val id: Long,
    val title: String,
    @Json(name = "poster_path")
    val posterPath: String,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
)