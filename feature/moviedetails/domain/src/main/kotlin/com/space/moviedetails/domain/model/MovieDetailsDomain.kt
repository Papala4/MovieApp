package com.space.moviedetails.domain.model

data class MovieDetailsDomain(
    val id: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val genres: List<String>,
    val runtime: Int,
    val releaseYear: String,
    val voteAverage: Double,
    val isFavourite: Boolean = false
)
