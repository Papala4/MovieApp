package com.space.moviedetails.model

data class MovieDetailsUi(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val genre: String?,
    val runtime: String,
    val releaseYear: String,
    val voteAverage: String,
    val isFavourite: Boolean = false
)