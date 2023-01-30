package com.example.movies.movies.repository

import com.example.movies.movies.model.MoviesResult

interface MoviesRepo {
    suspend fun returnDataFromApi():List<MoviesResult>
    suspend fun refreshData(list:List<MoviesResult>)
    suspend fun getMoviesByDatabase(): List<MoviesResult>
}