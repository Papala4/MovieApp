package com.space.moviedetails.remote.mapper

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.remote.dto.MovieDetailsDto

class MovieDetailsMapper {
    fun map(dto: MovieDetailsDto) = MovieDetailsResponse(
        id = dto.id,
        title = dto.title,
        tagline = dto.tagline.orEmpty(),
        overview = dto.overview,
        posterPath = dto.posterPath?.let { "$IMAGE_BASE_URL$it" }.orEmpty(),
        genre = dto.genres.firstOrNull()?.name,
        runtime = dto.runtime,
        releaseYear = dto.releaseDate.take(4),
        voteAverage = dto.voteAverage
    )

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500" //ar mikveboda
    }
}

//basemapper