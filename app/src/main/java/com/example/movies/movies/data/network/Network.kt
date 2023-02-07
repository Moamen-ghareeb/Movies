package com.example.movies.movies.data.network

import com.example.movies.movies.model.Movies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
        @GET("popular?")
       suspend fun getPopularMovies(@Query("api_key") api_key : String) : Response<Movies>
}

val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()