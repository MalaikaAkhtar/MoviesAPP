package com.example.moviesapi.response

import com.example.moviesapi.model.Movie

data class MovieResponse(
    val results: List<Movie>
)

