package com.space.data.local.mapper

import com.space.data.local.entity.FavouriteMovieEntity
import com.space.domain.model.FavouriteMovie

class FavouriteMovieMapper {

    fun mapToEntity(movie: FavouriteMovie) = FavouriteMovieEntity(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterUrl,
        releaseYear = movie.releaseYear,
        genre = movie.genre
    )

    fun mapToDomain(entity: FavouriteMovieEntity) = FavouriteMovie(
        id = entity.id,
        title = entity.title,
        posterUrl = entity.posterUrl,
        releaseYear = entity.releaseYear,
        genre = entity.genre
    )
}
