package com.example.mymovielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.databinding.MovieListItemBinding

class MovieListAdapter : ListAdapter<NetworkMovie, MovieViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<NetworkMovie>() {
        override fun areItemsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem.overview == newItem.overview
                    && oldItem.posterPath == newItem.posterPath
                    && oldItem.title == newItem.title
        }
    }
}

class MovieViewHolder(private val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: NetworkMovie) {
        val imageView = binding.moviePoster
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(imageView)
        binding.executePendingBindings()
    }
}
