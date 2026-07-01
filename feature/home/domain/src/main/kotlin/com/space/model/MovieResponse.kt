package com.space.model

data class MovieResponse(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseYear: String,
    val genreIds: List<Int>,
    val isFavourite: Boolean = false
)
