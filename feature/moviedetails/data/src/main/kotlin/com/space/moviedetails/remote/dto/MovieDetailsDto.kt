package com.space.moviedetails.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val overview: String = "",
    @SerialName("poster_path")
    val posterPath: String? = null,
    val genres: List<GenreDto> = emptyList(),
    val runtime: Int = 0,
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)