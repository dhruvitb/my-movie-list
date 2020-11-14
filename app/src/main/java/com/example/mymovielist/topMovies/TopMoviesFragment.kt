package com.example.mymovielist.topMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovielist.MovieListAdapter
import com.example.mymovielist.databinding.FragmentTopMoviesBinding

class TopMoviesFragment : Fragment() {

    private val viewModel: TopMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopMoviesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieListAdapter = MovieListAdapter()
        binding.movieList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieListAdapter
        }

        viewModel.topMovies.observe(viewLifecycleOwner, {
            movieListAdapter.submitList(it)
        })

        return binding.root
    }
}
