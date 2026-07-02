package com.space.remote.mapper

import com.space.model.MovieResponse
import com.space.network.BuildConfig.IMAGE_BASE_URL
import com.space.remote.dto.MovieDto

class PopularMovieMapper {
    fun map(dto: MovieDto) = MovieResponse(
        id = dto.id,
        title = dto.title,
        posterPath = dto.posterPath?.let { "$IMAGE_BASE_URL$it" }.orEmpty(),
        releaseYear = dto.releaseDate.take(4),
        genreIds = dto.genreIds
    )
}
