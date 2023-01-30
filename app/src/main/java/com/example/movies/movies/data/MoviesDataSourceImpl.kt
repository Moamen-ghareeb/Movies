package com.example.movies.movies.data

import androidx.room.withTransaction
import com.example.movies.movies.data.database.MoviesDataBase
import com.example.movies.movies.data.network.MovieApi
import com.example.movies.movies.model.MoviesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesDataSourceImpl @Inject constructor(
    private val api: MovieApi,
    private val moviesDataBase: MoviesDataBase
) : MoviesDataSource {

    override suspend fun getMoviesByApi(): List<MoviesResult> {
        return withContext(Dispatchers.IO) {
            api.getPopularMovies("69d66957eebff9666ea46bd464773cf0")
                .body()!!.moviesMoviesResults
        }
    }

    override suspend fun getMoviesByDatabase(): List<MoviesResult> {
        return withContext(Dispatchers.IO) {
            moviesDataBase.moviesDao().getMovies()
        }
    }

    override suspend fun deleteMoviesFromDatabase() {
        return withContext(Dispatchers.IO) {
            moviesDataBase.moviesDao().deleteAllMovies()
        }
    }

    override suspend fun insertMoviesInDatabase(list: List<MoviesResult>) {
        withContext(Dispatchers.IO) {
            moviesDataBase.withTransaction {
                moviesDataBase.moviesDao().insertMovies(list)
            }
        }
    }
}