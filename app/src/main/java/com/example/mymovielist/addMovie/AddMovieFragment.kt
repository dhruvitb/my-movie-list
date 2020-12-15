package com.example.mymovielist.addMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.database.AppDatabase
import com.example.mymovielist.databinding.FragmentAddMovieBinding

class AddMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args: AddMovieFragmentArgs by navArgs()
        val binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        val database = AppDatabase.getInstance(requireContext())
        val viewModel: AddMovieViewModel by viewModels {
            AddMovieViewModelFactory(database)
        }

        val movie = args.movie
        binding.apply {
            Glide.with(this@AddMovieFragment)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(moviePoster)
            movieTitle.text = movie.title
            addToList.setOnClickListener {
                viewModel.addToMyMovies(
                    movie,
                    binding.myRating.value.toDouble(),
                    binding.notes.text.toString()
                )
                binding.notes.clearFocus()
                // TODO more stuff with bottom nav
                findNavController().navigate(R.id.my_movies_fragment)
            }
        }

        return binding.root
    }
}