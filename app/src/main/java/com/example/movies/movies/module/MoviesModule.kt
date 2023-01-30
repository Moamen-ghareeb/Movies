package com.example.movies.movies.module

import android.content.Context
import androidx.room.Room
import com.example.movies.movies.data.database.MoviesDao
import com.example.movies.movies.data.database.MoviesDataBase
import com.example.movies.movies.data.network.MovieApi
import com.example.movies.movies.data.network.moshi
import com.example.task_.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()


    @Provides
    fun apiProvider(retrofit: Retrofit) : MovieApi =retrofit.create(MovieApi::class.java)


    @Singleton
    @Provides
    fun getMoviesDatabase(
        @ApplicationContext context: Context
    ): MoviesDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDataBase::class.java,
            "movies_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun moviesDao(database: MoviesDataBase): MoviesDao {
        return database.moviesDao()
    }
}