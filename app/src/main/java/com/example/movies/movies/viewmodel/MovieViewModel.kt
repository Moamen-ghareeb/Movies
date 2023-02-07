package com.example.movies.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.movies.model.MoviesResult
import com.example.movies.movies.model.Resultf
import com.example.movies.movies.repository.MoviesRepo
import kotlinx.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private var repo: MoviesRepo) : ViewModel() {

    val localMoviesList: MutableLiveData<List<MoviesResult>> by lazy {
        MutableLiveData<List<MoviesResult>>()
    }

    init {
        viewModelScope.launch {
            try {
                (Resultf.Success(repo.returnDataFromApi()))
                repo.refreshData(repo.returnDataFromApi())
                localMoviesList.postValue(repo.getMoviesByDatabase())
            } catch (exception: Exception) {
                localMoviesList.postValue(repo.getMoviesByDatabase())
                when (exception) {
                    is IOException -> {
                        (Resultf.Error(exception.message.toString()))
                    }
                    is IllegalStateException -> {
                        (Resultf.Error(exception.message.toString()))
                    }
                    is HttpException -> {
                        (Resultf.Error(exception.message.toString()))
                    }
                }
            }

        }
    }
}
