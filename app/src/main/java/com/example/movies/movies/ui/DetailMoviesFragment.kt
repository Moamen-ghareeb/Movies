package com.example.movies.movies.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.task_.R
import com.example.task_.databinding.FragmentDetailMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMoviesFragment : Fragment() {
    private lateinit var binding: FragmentDetailMoviesBinding
    private val args: DetailMoviesFragmentArgs  by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoPath = args.movie.poster_path
        Glide
            .with(binding.moviePoster.context)
            .load("https://image.tmdb.org/t/p/w500$logoPath")
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image)
            .into(binding.moviePoster)
        binding.movieTitle.text=args.movie.title.toString()
        binding.moviesDetial.text=args.movie.overview.toString()

    }
}
