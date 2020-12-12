package com.example.mymovielist.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.database.AppDatabase
import com.example.mymovielist.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args: MovieDetailFragmentArgs by navArgs()
        val binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        val movie = args.movie
        val database = AppDatabase.getInstance(requireContext())
        val viewModel: MovieDetailViewModel by viewModels {
            MovieDetailViewModelFactory(database)
        }
        binding.apply {
            movieTitle.text = movie.title
            Glide.with(this@MovieDetailFragment)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(moviePoster)
            movieOverview.text = movie.overview
            releaseDate.text = resources.getString(R.string.release_date, movie.releaseDate)
            rating.text = resources.getString(R.string.rating, movie.rating.toString())
            addToList.setOnClickListener {
                viewModel.addToMovieList(movie)
            }
        }
        return binding.root
    }
}