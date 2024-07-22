package com.example.moviesapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.moviesapi.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieTitle: TextView = findViewById(R.id.movieTitle)
        val movieImage: ImageView = findViewById(R.id.poster)
        val watchNowButton: MaterialCardView = findViewById(R.id.watchBtn)

        val trailerUrl = intent.getStringExtra("TRAILER_URL")
        val posterPath = intent.getStringExtra("poster_path")

        movieTitle.text = intent.getStringExtra("title")
        Picasso.get().load("https://image.tmdb.org/t/p/w500/$posterPath").into(movieImage)

        watchNowButton.setOnClickListener {
            val intent = Intent(this@MovieDetailActivity,TrailerActivity::class.java).apply{
                putExtra("TRAILER_URL", trailerUrl)
            }
            startActivity(intent)
        }
    }
}