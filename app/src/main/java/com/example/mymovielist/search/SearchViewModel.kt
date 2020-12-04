package com.example.mymovielist.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymovielist.api.NetworkMovie
import kotlinx.coroutines.flow.Flow

class SearchViewModel : ViewModel() {

    private var currentQuery: String? = null

    var currentSearchResult: Flow<PagingData<NetworkMovie>>? = null
        private set

    fun getSearchResultsFlow(query: String): Flow<PagingData<NetworkMovie>> {
        val lastResult = currentSearchResult
        if (currentQuery == query && lastResult != null) {
            return lastResult
        }
        currentQuery = query
        val newSearchResult = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviesPagingSource(query) }
        ).flow.cachedIn(viewModelScope)
        currentSearchResult = newSearchResult
        return newSearchResult
    }
}