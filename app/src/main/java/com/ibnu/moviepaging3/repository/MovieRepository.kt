package com.ibnu.moviepaging3.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ibnu.moviepaging3.data.remote.factory.MoviePagingSource
import com.ibnu.moviepaging3.data.remote.network.MovieService
import com.ibnu.moviepaging3.data.remote.response.MovieResponse

class MovieRepository(private val apiService: MovieService) {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(apiService: MovieService): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(apiService)
            }
    }

    fun getTopRatedMovies(): LiveData<PagingData<MovieResponse>> = Pager(
        config = PagingConfig(15),
    ) {
        MoviePagingSource(apiService)
    }.liveData

}