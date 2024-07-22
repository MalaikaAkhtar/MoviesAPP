package com.example.moviesapi.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.Toast
import com.example.moviesapi.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView



class TrailerActivity : AppCompatActivity() {

    private lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)


        youtubePlayerView = findViewById(R.id.youtubePlayerView)
        lifecycle.addObserver(youtubePlayerView)

        val trailerUrl = intent.getStringExtra("TRAILER_URL")
        val videoId = trailerUrl?.split("=")?.lastOrNull()

        if (videoId.isNullOrEmpty()) {
            Toast.makeText(this, "Trailer URL is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }
}
