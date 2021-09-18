package com.ibnu.moviepaging3

import androidx.lifecycle.ViewModel
import com.ibnu.moviepaging3.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getTopRatedMovie() = repository.getTopRatedMovies()
}