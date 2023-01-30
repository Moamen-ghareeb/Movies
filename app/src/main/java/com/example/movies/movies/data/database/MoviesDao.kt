package com.example.movies.movies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.movies.model.MoviesResult

@Dao
interface MoviesDao {

    @Query("SELECT * from MoviesEntity ORDER BY popularity ASC")
    fun getMovies(): List<MoviesResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertMovies (movies : List<MoviesResult>)

    @Query("DELETE FROM MoviesEntity")
    suspend fun deleteAllMovies()
}