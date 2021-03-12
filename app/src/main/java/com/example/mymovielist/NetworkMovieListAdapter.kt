package com.example.mymovielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.api.NetworkMovie
import com.example.mymovielist.databinding.MovieListItemBinding

class MovieListAdapter(private val movieList: MovieListType) : PagingDataAdapter<NetworkMovie, MovieViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            movieList
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            holder.bind(movieItem)
        }
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

class MovieViewHolder(private val binding: MovieListItemBinding, private val movieList: MovieListType) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: NetworkMovie) {
        val movieListItemListener = MovieListItemListener {
            if (movieList == MovieListType.SEARCH) {
                itemView.findNavController().navigate(R.id.action_search_fragment_to_movie_detail_fragment, bundleOf("movie" to movie))
            } else if (movieList == MovieListType.TOP_MOVIES) {
                itemView.findNavController().navigate(R.id.action_top_movies_fragment_to_movie_detail_fragment, bundleOf("movie" to movie))
            }
        }
        binding.apply {
            Glide.with(moviePoster)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(moviePoster)
            onClickListener = movieListItemListener
            executePendingBindings()
        }
    }
}

class MovieListItemListener(private val onClickListener: () -> Unit) {
    fun onClick() = onClickListener()
}

enum class MovieListType {
    TOP_MOVIES, SEARCH
}