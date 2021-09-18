package com.ibnu.moviepaging3.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("original_title")
    val movieName: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genre_ids")
    val genres: List<Int>,

    @SerializedName("id")
    val id: String,

    @SerializedName("original_language")
    val language: String,
)
