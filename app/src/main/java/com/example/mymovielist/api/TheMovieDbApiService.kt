package com.example.mymovielist.api

import com.example.mymovielist.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface TheMovieDbService {
    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopMovies(@Query("page") page: Int): MoviesListResponse

    @GET("search/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MoviesListResponse

    data class MoviesListResponse(val results: List<NetworkMovie>)
}

object TheMovieDbApi {
    val retrofitService: TheMovieDbService by lazy { retrofit.create(TheMovieDbService::class.java) }
}
