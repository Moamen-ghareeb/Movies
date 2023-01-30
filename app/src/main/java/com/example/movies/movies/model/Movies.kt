package com.example.movies.movies.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movies(
    val page: Int,
    @Json(name="results")
    val moviesMoviesResults: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable


@Parcelize
@Entity(tableName = "MoviesEntity")
data class MoviesResult(
    val adult: Boolean,
    @PrimaryKey
    val id: Int,
    @field:Json(name = "original language")
    val original_language: String,
    @field:Json(name = "original title")
    val original_title: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "poster path")
    val poster_path: String,
    @field:Json(name = "release date")
    val release_date: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "video")
    val video: Boolean,
    @field:Json(name = "vote average")
    val vote_average: Double,
    @field:Json(name = "vote count")
    val vote_count: Int
) : Parcelable

