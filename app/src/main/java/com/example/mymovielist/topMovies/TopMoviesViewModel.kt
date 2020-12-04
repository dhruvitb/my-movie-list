package com.example.mymovielist.topMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class TopMoviesViewModel : ViewModel() {
    val topMoviesFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TopMoviesPagingSource() }
    ).flow.cachedIn(viewModelScope)
}
