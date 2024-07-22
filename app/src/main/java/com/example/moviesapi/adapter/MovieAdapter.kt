package com.example.moviesapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapi.R
import com.example.moviesapi.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private var movies: List<Movie>,
                   private val onItemClick: (Movie) -> Unit ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    class MovieViewHolder(itemView: View, private val onItemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val poster: ImageView = itemView.findViewById(R.id.poster)

        fun bind(movie: Movie) {
            title.text = movie.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(poster)

            itemView.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

}