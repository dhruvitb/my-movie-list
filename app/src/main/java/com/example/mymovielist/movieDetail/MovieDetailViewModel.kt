package com.example.mymovielist.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.api.toDatabaseMovie
import com.example.mymovielist.database.AppDatabase
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val database: AppDatabase) : ViewModel() {
    fun addToMovieList(movie: NetworkMovie) {
        viewModelScope.launch {
            database.movieDao.add(movie.toDatabaseMovie())
        }
    }
}

class MovieDetailViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
