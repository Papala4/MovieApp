package com.space.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val year: String,
    val genre: String,
    val isFavorite: Boolean = false
)
