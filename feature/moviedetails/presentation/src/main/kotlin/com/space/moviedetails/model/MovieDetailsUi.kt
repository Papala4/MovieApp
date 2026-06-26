package com.space.moviedetails.model

data class MovieDetailsUi(
    val id: Int,
    val title: String,
    val tagline: String?,
    val overview: String,
    val posterPath: String,
    val genre: String?,
    val runtime: Int,
    val releaseYear: String,
    val voteAverage: Double,
    val isFavourite: Boolean = false
)