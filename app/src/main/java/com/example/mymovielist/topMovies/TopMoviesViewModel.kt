package com.example.mymovielist.topMovies

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymovielist.api.NetworkMovie
import kotlinx.coroutines.flow.Flow

class TopMoviesViewModel : ViewModel() {

    fun getTopMoviesFlow(): Flow<PagingData<NetworkMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopMoviesPagingSource() }
        ).flow
    }
}
