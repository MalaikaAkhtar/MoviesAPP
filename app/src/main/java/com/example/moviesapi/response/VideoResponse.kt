package com.example.moviesapi.response

import com.example.moviesapi.model.Video

data class VideoResponse(
    val results: List<Video>
)