package com.space.moviedetails.remote.mapper

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.remote.dto.MovieDetailsDto
import com.space.network.BuildConfig

class MovieDetailsMapper {
    fun map(dto: MovieDetailsDto) = MovieDetailsResponse(
        id = dto.id,
        title = dto.title,
        overview = dto.overview,
        posterPath = dto.posterPath?.let { "${BuildConfig.IMAGE_BASE_URL}$it" }.orEmpty(),
        genre = dto.genres.firstOrNull()?.name,
        runtime = dto.runtime,
        releaseYear = dto.releaseDate.take(4),
        voteAverage = dto.voteAverage
    )
}
