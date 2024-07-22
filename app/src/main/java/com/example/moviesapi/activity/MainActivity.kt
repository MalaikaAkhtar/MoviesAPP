package com.example.moviesapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapi.R
import com.example.moviesapi.adapter.MovieAdapter
import com.example.moviesapi.adapter.ParentAdapter
import com.example.moviesapi.model.ParentItem
import com.example.moviesapi.viewModel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var parentAdapter: ParentAdapter
    private var parentList = ArrayList<ParentItem>()
    private val apiKey = "ffa85e58c7a367b0a6b6798a7b2599d7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.parentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.movies.observe(this, Observer { movies ->
            val parentItem = ParentItem("Popular Movies", movies)
            parentList.add(parentItem)
            updateAdapter()
        })

        viewModel.upcoming.observe(this, Observer { movies ->
            val parentItem = ParentItem("Upcoming Movies", movies)
            parentList.add(parentItem)
            updateAdapter()
        })

        viewModel.nowPlayingMovies.observe(this, Observer { movies ->
            val parentItem = ParentItem("Now Playing Movies", movies)
            parentList.add(parentItem)
            updateAdapter()
        })

        viewModel.topRated.observe(this, Observer { movies ->
            val parentItem = ParentItem("Top_Rated Movies", movies)
            parentList.add(parentItem)
            updateAdapter()
        })

        viewModel.fetchPopularMovies(apiKey)
        viewModel.fetchTopRatedMovies(apiKey)
        viewModel.fetchUpcomingMovies(apiKey)
        viewModel.fetchNowPlayingMovies(apiKey)


        spinners()
    }


    private fun updateAdapter() {
            parentAdapter = ParentAdapter(parentList) { movie ->
                val intent = Intent(this, MovieDetailActivity::class.java).apply {
                    putExtra("title", movie.title)
                    putExtra("poster_path", movie.poster_path)
                    putExtra("TRAILER_URL", movie.trailer_url)
                }
                startActivity(intent)
            }
            recyclerView.adapter = parentAdapter
    }

    private fun spinners() {
        val spinner = findViewById<Spinner>(R.id.spinID)
        val movie = arrayOf("Tom & Jerry", "Mr Bean", "Avatar")
        val arrayAdapter =  ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, movie)
        spinner.adapter = arrayAdapter

        val spinner2 = findViewById<Spinner>(R.id.spin)
        val webSeries = arrayOf("Money Hiest", "Wednesday", "Avatar")
        val arrayAdp =  ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, webSeries)
        spinner2.adapter = arrayAdp

        val spinner3 = findViewById<Spinner>(R.id.spinID2)
        val genres = arrayOf("Program1", "Program2", "Program3")
        val array =  ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, genres)
        spinner3.adapter = array
    }
}