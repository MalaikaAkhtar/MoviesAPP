package com.example.moviesapi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.model.Movie
import com.example.moviesapi.repository.MovieRepository
import com.example.moviesapi.retrofit.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>> = _movies

    private val _topRated = MutableLiveData<List<Movie>>()
    val topRated : LiveData<List<Movie>> = _topRated

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> = _nowPlayingMovies

    private val _upcoming = MutableLiveData<List<Movie>>()
    val upcoming : LiveData<List<Movie>> = _upcoming


    //val apiKey = "ffa85e58c7a367b0a6b6798a7b2599d7"

    fun fetchPopularMovies(apiKey: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPopularMovies(apiKey)
                val moviesWithTrailers = response.results.map { movie ->
                    val videoResponse = repository.getMovieVideos(movie.id, apiKey)
                    val trailer = videoResponse.results.find { it.type == "Trailer" && it.site == "YouTube" }
                    movie.copy(trailer_url = trailer?.let { "https://www.youtube.com/watch?v=${it.key}" })
                }
                _movies.postValue(moviesWithTrailers)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching popular movies", e)
            }
        }
    }

    fun fetchTopRatedMovies(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTopRatedMovies(apiKey)
                val moviesWithTrailers = response.results.map { movie ->
                    val videoResponse = repository.getMovieVideos(movie.id, apiKey)
                    val trailer = videoResponse.results.find { it.type == "Trailer" && it.site == "YouTube" }
                    movie.copy(trailer_url = trailer?.let { "https://www.youtube.com/watch?v=${it.key}" })
                }
                _topRated.postValue(moviesWithTrailers)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching now playing movies", e)
            }
        }
    }


    fun fetchNowPlayingMovies(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNowPlayingMovies(apiKey)
                val moviesWithTrailers = response.results.map { movie ->
                    val videoResponse = repository.getMovieVideos(movie.id, apiKey)
                    val trailer = videoResponse.results.find { it.type == "Trailer" && it.site == "YouTube" }
                    movie.copy(trailer_url = trailer?.let { "https://www.youtube.com/watch?v=${it.key}" })
                }
                _nowPlayingMovies.postValue(moviesWithTrailers)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching now playing movies", e)
            }
        }
    }

    fun fetchUpcomingMovies(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getUpcomingMovies(apiKey)
                val moviesWithTrailers = response.results.map { movie ->
                    val videoResponse = repository.getMovieVideos(movie.id, apiKey)
                    val trailer = videoResponse.results.find { it.type == "Trailer" && it.site == "YouTube" }
                    movie.copy(trailer_url = trailer?.let { "https://www.youtube.com/watch?v=${it.key}" })
                }
                _upcoming.postValue(moviesWithTrailers)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching now playing movies", e)
            }
        }
    }



}

