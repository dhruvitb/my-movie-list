package com.example.mymovielist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my-movies")
class DatabaseMovie(
    @PrimaryKey
    val id: Long,
    val title: String,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val myRating: Double,
    val notes: String?
)
