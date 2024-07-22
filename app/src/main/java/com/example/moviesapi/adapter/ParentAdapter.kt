package com.example.moviesapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapi.R
import com.example.moviesapi.model.Movie
import com.example.moviesapi.model.ParentItem

class ParentAdapter (
    private val parentList: List<ParentItem>,
    private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<ParentAdapter.MovieViewHolder>()
    {

        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val movieTitle: TextView = itemView.findViewById(R.id.parentTitle)
            val childRecyclerView: RecyclerView = itemView.findViewById(R.id.trendingRV)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
            return MovieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val parentItem = parentList[position]
            holder.movieTitle.text = parentItem.title
            holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = MovieAdapter(parentItem.list, onClick)
            holder.childRecyclerView.adapter = adapter
        }

        override fun getItemCount() = parentList.size
    }
