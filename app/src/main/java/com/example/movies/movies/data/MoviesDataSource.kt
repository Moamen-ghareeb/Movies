package com.example.movies.movies.data

import com.example.movies.movies.model.MoviesResult


interface MoviesDataSource {

    suspend fun getMoviesByApi():List<MoviesResult>
    suspend fun insertMoviesInDatabase(list: List<MoviesResult>)
    suspend fun getMoviesByDatabase():List<MoviesResult>
    suspend fun deleteMoviesFromDatabase()
}