package com.example.mymovielist.topMovies

import androidx.paging.PagingSource
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.api.TheMovieDbApi
import retrofit2.HttpException
import java.io.IOException

const val THE_MOVIE_DB_MIN_PAGE = 1
const val THE_MOVIE_DB_MAX_PAGE = 1000

class TopMoviesPagingSource : PagingSource<Int, NetworkMovie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        val index = params.key ?: THE_MOVIE_DB_MIN_PAGE
        return try {
            val response = TheMovieDbApi.retrofitService.getTopMovies(index)
            val topMovies = response.results
            LoadResult.Page(
                topMovies,
                if (index == THE_MOVIE_DB_MIN_PAGE) null else index - 1,
                if (index == THE_MOVIE_DB_MAX_PAGE) null else index + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}