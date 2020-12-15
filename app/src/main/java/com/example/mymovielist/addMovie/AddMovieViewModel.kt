package com.example.mymovielist.addMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.api.toDatabaseMovie
import com.example.mymovielist.database.AppDatabase
import kotlinx.coroutines.launch

class AddMovieViewModel(private val database: AppDatabase) : ViewModel() {
    fun addToMyMovies(movie: NetworkMovie, myRating: Double, notes: String?) {
        viewModelScope.launch {
            database.movieDao.add(movie.toDatabaseMovie(myRating, notes))
        }
    }
}

class AddMovieViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMovieViewModel::class.java)) {
            return AddMovieViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}