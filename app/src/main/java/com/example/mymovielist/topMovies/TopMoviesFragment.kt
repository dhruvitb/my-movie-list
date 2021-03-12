package com.example.mymovielist.topMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovielist.MovieListAdapter
import com.example.mymovielist.MovieListType
import com.example.mymovielist.databinding.FragmentTopMoviesBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopMoviesFragment : Fragment() {

    private val viewModel: TopMoviesViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTopMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val movieListAdapter = MovieListAdapter(MovieListType.TOP_MOVIES)
        binding.movieList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieListAdapter
        }

        lifecycleScope.launch {
            viewModel.topMoviesFlow.collectLatest {
                movieListAdapter.submitData(it)
            }
        }

        return binding.root
    }
}
