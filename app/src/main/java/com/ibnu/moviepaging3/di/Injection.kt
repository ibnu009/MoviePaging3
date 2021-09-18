package com.ibnu.moviepaging3.di

import android.graphics.Movie
import com.ibnu.moviepaging3.data.remote.RetrofitApp
import com.ibnu.moviepaging3.repository.MovieRepository

object Injection {

    private val provideMovieService = RetrofitApp.getMovieService()

    fun provideMovieRepository(): MovieRepository = MovieRepository.getInstance(provideMovieService)
}