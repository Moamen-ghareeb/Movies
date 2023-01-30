package com.example.movies.movies.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.movies.movies.adapter.MovieAdapter
import com.example.movies.movies.model.Resultf
import com.example.movies.movies.viewmodel.MovieViewModel
import com.example.movies.utility.ConnectionLiveData
import com.example.task_.R
import com.example.task_.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var connectionLiveData: ConnectionLiveData
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false)
        prepareRecyclerView()
        setMoviesListToAdapterFromLocalDatabase()
        connectionLiveData = ConnectionLiveData(this.requireContext())
        connectionLiveData.observe(this.viewLifecycleOwner) { isNetworkAvailable ->
            if (isNetworkAvailable) {
                setMoviesListToAdapterFromApi()
            } else {
                setMoviesListToAdapterFromLocalDatabase()
            }
        }
        return binding.root

    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter {
            val navHostFragment = MoviesListFragmentDirections.actionMoviesListToDetailMovies(it)
            findNavController().navigate(navHostFragment)
        }
        binding.rvMovies.apply {
            adapter = movieAdapter
        }

    }

    private fun setMoviesListToAdapterFromLocalDatabase() {
        viewModel.getMoviesListLocal()
        viewModel.localMoviesList.observe(this.viewLifecycleOwner, Observer {
            movieAdapter.submitList(it)
        })
    }

    private fun setMoviesListToAdapterFromApi() {
        viewModel.getMoviesFromApi()
        viewModel.movieLiveDataApi.observe(this.viewLifecycleOwner) { movies ->
            if (movies is Resultf.Success) {
                viewModel.movieLiveDataApi.observe(this.viewLifecycleOwner, Observer {
                    movieAdapter.submitList(movies.data)
                })
                viewModel.refreshData()
            } else if (movies is Resultf.Error) {
                Log.i("error", movies.exception.toString())
                Toast.makeText(
                    context, "This ERROR OCCUR \n ${movies.exception}", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}








