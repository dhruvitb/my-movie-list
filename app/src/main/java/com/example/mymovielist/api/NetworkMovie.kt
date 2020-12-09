package com.example.mymovielist.api

import android.os.Parcelable
import com.example.mymovielist.database.DatabaseMovie
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkMovie(
    val id: Long,
    val title: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    val overview: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "vote_average")
    val rating: Double
) : Parcelable

fun NetworkMovie.toDatabaseMovie(): DatabaseMovie {
    return DatabaseMovie(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        myRating = null,
        notes = null
    )
}