package com.example.movies.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.movies.model.MoviesResult
import com.example.task_.R
import com.example.task_.databinding.MovieLayoutBinding

class MovieAdapter(private val listener:(MoviesResult)->Unit)
:  ListAdapter<MoviesResult, MovieAdapter.ViewHolder>(DiffCallback){

     class ViewHolder( val binding: MovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieLayoutBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val items =  getItem(position)
        holder.binding.moviesData.text= items.release_date
        holder.binding.movieName.text= items.title
        holder.itemView.setOnClickListener{
            listener(items)
        }
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500" + items.poster_path)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image)
            .into(holder.binding.movieImage)
}
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MoviesResult>() {
            override fun areItemsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}


