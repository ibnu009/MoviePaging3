package com.ibnu.moviepaging3.data.remote.network

import com.ibnu.moviepaging3.data.remote.response.ListMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(): Response<ListMovieResponse>

}