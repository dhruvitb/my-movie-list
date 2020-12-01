package com.example.mymovielist.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovielist.MovieListAdapter
import com.example.mymovielist.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val listAdapter = MovieListAdapter()
        binding.searchResultsList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    lifecycleScope.launch {
                        viewModel.getSearchResultsFlow(query.toUri().toString()).collectLatest {
                            listAdapter.submitData(it)
                        }
                    }
                    binding.searchBar.clearFocus()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }

        binding.searchBar.setOnQueryTextListener(queryTextListener)

        return binding.root
    }
}
