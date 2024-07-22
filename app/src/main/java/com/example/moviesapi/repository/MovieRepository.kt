package com.example.moviesapi.repository

import com.example.moviesapi.retrofit.RetrofitInstance

class MovieRepository {

    private val api = RetrofitInstance.moviesApi

    suspend fun getPopularMovies(apiKey: String) = api.getPopularMovies(apiKey)

    suspend fun getTopRatedMovies(apiKey: String) = api.getTopRatedMovies(apiKey)

    suspend fun getNowPlayingMovies(apiKey: String) = api.getNowPlayingMovies(apiKey)

    suspend fun getUpcomingMovies(apiKey: String) = api.getUpcomingMovies(apiKey)

    suspend fun getMovieVideos(movieId: Int, apiKey: String) = api.getMovieVideos(movieId, apiKey)
}

