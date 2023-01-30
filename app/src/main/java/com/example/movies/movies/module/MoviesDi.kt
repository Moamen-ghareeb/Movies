package com.example.movies.movies.module

import android.app.Application
import android.content.Context
import com.example.movies.movies.data.MoviesDataSource
import com.example.movies.movies.data.MoviesDataSourceImpl
import com.example.movies.movies.data.database.MoviesDataBase
import com.example.movies.movies.data.network.MovieApi
import com.example.movies.movies.repository.MoviesRepo
import com.example.movies.movies.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MoviesDi {

    @Singleton
    @Provides
    fun provideDataSource(api: MovieApi,moviesDataBase: MoviesDataBase ):MoviesDataSource{
        return MoviesDataSourceImpl(api, moviesDataBase)
    }

    @Singleton
    @Provides
    fun provideRepo(dataSource: MoviesDataSource):MoviesRepo{
        return MoviesRepositoryImpl(dataSource)
    }


    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}