package com.ibnu.moviepaging3.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibnu.moviepaging3.data.remote.RetrofitApp.Companion.POSTER_PATH
import com.ibnu.moviepaging3.data.remote.response.MovieResponse
import com.ibnu.moviepaging3.databinding.ItemMovieBinding

class MovieAdapter() :
    PagingDataAdapter<MovieResponse, MovieAdapter.MovieViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse) {
            binding.tvTitleMovie.text = movie.movieName
            binding.tvCategoryMovie.text = movie.genres.toString()

            Glide.with(itemView.context)
                .load("${POSTER_PATH}${movie.posterPath}")
                .into(binding.imgCoverMovie)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieResponse> =
            object : DiffUtil.ItemCallback<MovieResponse>() {
                override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                    return oldItem.id == newItem.id && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieResponse,
                    newItem: MovieResponse
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}