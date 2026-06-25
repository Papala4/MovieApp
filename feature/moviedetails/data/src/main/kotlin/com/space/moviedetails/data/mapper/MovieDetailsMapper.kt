package com.space.moviedetails.data.mapper

import com.space.moviedetails.data.remote.dto.MovieDetailsDto
import com.space.moviedetails.domain.model.MovieDetailsDomain

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDetailsDto.toDomain() = MovieDetailsDomain(
    id = id,
    title = title,
    tagline = tagline.orEmpty(),
    overview = overview,
    posterPath = posterPath?.let { "$IMAGE_BASE_URL$it" }.orEmpty(),
    genres = genres.map { it.name },
    runtime = runtime,
    releaseYear = releaseDate.take(4),
    voteAverage = voteAverage
)
