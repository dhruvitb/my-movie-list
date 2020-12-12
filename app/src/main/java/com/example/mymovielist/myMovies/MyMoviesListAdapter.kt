package com.example.mymovielist.myMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.database.DatabaseMovie
import com.example.mymovielist.databinding.MyMoviesListItemBinding

class MyMoviesListAdapter : ListAdapter<DatabaseMovie, ViewHolder>(MyMoviesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyMoviesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ViewHolder(private val binding: MyMoviesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: DatabaseMovie) {
        binding.movieTitle.text = movie.title
        binding.executePendingBindings()
    }
}

class MyMoviesDiffCallback : DiffUtil.ItemCallback<DatabaseMovie>() {
    override fun areItemsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
        return oldItem.title == newItem.title
                && oldItem.myRating == newItem.myRating
                && oldItem.notes == newItem.notes
                && oldItem.posterPath == newItem.posterPath
    }
}