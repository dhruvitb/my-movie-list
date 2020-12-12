package com.example.mymovielist.myMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovielist.database.AppDatabase

class MyMoviesViewModel(database: AppDatabase) : ViewModel() {
    val myMovies = database.movieDao.getAll()
}

class MyMoviesViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MyMoviesViewModel::class.java)) {
            return MyMoviesViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}