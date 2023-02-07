package com.example.movies.movies.ui


import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.movies.adapter.MovieAdapter
import com.example.movies.movies.viewmodel.MovieViewModel
import com.example.task_.R
import com.example.task_.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        setMoviesListToAdapterFromLocalDatabase()

    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter {
            val navHostFragment = MoviesListFragmentDirections.actionMoviesListToDetailMovies(it)
            findNavController().navigate(navHostFragment)
        }
        binding.rvMovies.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding.rvMovies.apply {
            adapter = movieAdapter
        }

    }

    private fun setMoviesListToAdapterFromLocalDatabase() {
        viewModel.localMoviesList.observe(this.viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
        })
    }

}








