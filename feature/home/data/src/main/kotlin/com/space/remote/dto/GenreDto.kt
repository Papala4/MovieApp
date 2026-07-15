package com.space.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenresDto(
    val genres: List<GenreDto>
)
@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)