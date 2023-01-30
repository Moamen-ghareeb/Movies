package com.example.movies.movies.data.network

import com.example.movies.movies.model.Movies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
        @GET("popular?")
       suspend fun getPopularMovies(@Query("api_key") api_key : String) : Response<Movies>
}

val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()