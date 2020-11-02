package com.example.mymovielist.topMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymovielist.databinding.FragmentTopMoviesBinding

class TopMoviesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopMoviesBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewModel = TopMoviesViewModel()
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }
}
