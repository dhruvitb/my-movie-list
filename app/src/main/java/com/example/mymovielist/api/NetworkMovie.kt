package com.example.mymovielist.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

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
