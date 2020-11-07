package com.example.mymovielist.topMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.api.TheMovieDbApi
import kotlinx.coroutines.launch

class TopMoviesViewModel : ViewModel() {

    private val _topMovies = MutableLiveData<List<NetworkMovie>>()
    val topMovies: LiveData<List<NetworkMovie>>
        get() = _topMovies

    init {
        getTopMovies()
    }

    private fun getTopMovies() {
        viewModelScope.launch {
            _topMovies.value = TheMovieDbApi.retrofitService.getTopMovies().results
        }
    }
}
