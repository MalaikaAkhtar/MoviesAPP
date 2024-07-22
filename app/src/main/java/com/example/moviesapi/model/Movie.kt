package com.example.moviesapi.model

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val trailer_url: String? = null
)