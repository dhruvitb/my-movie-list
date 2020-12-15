package com.example.mymovielist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: DatabaseMovie): Long

    @Delete
    suspend fun delete(movie: DatabaseMovie)

    @Query("SELECT * FROM `my-movies` ORDER BY myRating DESC")
    fun getAll(): LiveData<List<DatabaseMovie>>
}