package com.example.movies.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.movies.model.MoviesResult
import com.example.movies.movies.model.Resultf
import com.example.movies.movies.repository.MoviesRepo
import com.example.movies.utility.ConnectionLiveData
import kotlinx.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private var repo: MoviesRepo) : ViewModel() {

    val movieLiveDataApi: MutableLiveData<Resultf<List<MoviesResult>>> by lazy {
        MutableLiveData<Resultf<List<MoviesResult>>>()
    }

    val localMoviesList: MutableLiveData<List<MoviesResult>> by lazy {
        MutableLiveData<List<MoviesResult>>()
    }

    fun getMoviesListLocal() {
        viewModelScope.launch {
            localMoviesList.postValue(repo.getMoviesByDatabase())
        }
    }
    fun refreshData(){
        viewModelScope.launch{
            repo.refreshData(repo.returnDataFromApi())
        }
    }
     fun getMoviesFromApi() {
        viewModelScope.launch {
            try {
                movieLiveDataApi.postValue(Resultf.Success(repo.returnDataFromApi()))
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> {
                        movieLiveDataApi.postValue(Resultf.Error(exception.message.toString()))
                    }
                    is IllegalStateException -> {
                        movieLiveDataApi.postValue(Resultf.Error(exception.message.toString()))
                    }
                    is HttpException -> {
                        movieLiveDataApi.postValue(Resultf.Error(exception.message.toString()))
                    }
                }
            }

        }
    }
}
