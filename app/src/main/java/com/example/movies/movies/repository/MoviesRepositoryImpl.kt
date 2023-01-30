package com.example.movies.movies.repository


import com.example.movies.movies.data.MoviesDataSource
import com.example.movies.movies.model.MoviesResult
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(private val dataSource: MoviesDataSource) :
    MoviesRepo {


    override suspend fun returnDataFromApi(): List<MoviesResult> {
        return dataSource.getMoviesByApi()
    }

    override suspend fun refreshData(list: List<MoviesResult>) {
        dataSource.deleteMoviesFromDatabase()
        dataSource.insertMoviesInDatabase(list)
    }

    override suspend fun getMoviesByDatabase(): List<MoviesResult> {
        return dataSource.getMoviesByDatabase()
    }

}