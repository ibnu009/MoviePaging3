package com.ibnu.moviepaging3.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    val results: List<MovieResponse>,
    @field:SerializedName("total_pages")
    val totalPage: Int
)