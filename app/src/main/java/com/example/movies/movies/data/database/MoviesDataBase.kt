package com.example.movies.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.movies.model.MoviesResult


@Database(entities = [MoviesResult::class], version = 2, exportSchema = false)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}