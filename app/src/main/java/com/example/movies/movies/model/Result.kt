package com.example.movies.movies.model

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.task_.R

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

enum class ApiStatus { LOADING, ERROR, DONE }

@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

sealed class Resultf<out T : Any> {

    data class Success<out T : Any>(val data:T) : Resultf<T>()
    data class Error(val exception:Any) : Resultf<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}