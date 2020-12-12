package com.example.mymovielist.myMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.database.AppDatabase
import com.example.mymovielist.databinding.FragmentMyMoviesBinding

class MyMoviesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val movieListAdapter = MyMoviesListAdapter()

        binding.myMoviesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieListAdapter
        }

        val database = AppDatabase.getInstance(requireContext())
        val viewModel: MyMoviesViewModel by viewModels {
            MyMoviesViewModelFactory(database)
        }

        viewModel.myMovies.observe(viewLifecycleOwner) {
            movieListAdapter.submitList(it)
        }

        return binding.root
    }
}