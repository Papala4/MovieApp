package com.space.data.local.mapper

import com.space.data.local.entity.FavouriteMovieEntity
import com.space.domain.model.Movie

class FavouriteMovieMapper {

    fun mapToEntity(movie: Movie) = FavouriteMovieEntity(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterUrl,
        releaseYear = movie.year,
        genre = movie.genre
    )

    fun mapToDomain(entity: FavouriteMovieEntity) = Movie(
        id = entity.id,
        title = entity.title,
        posterUrl = entity.posterUrl,
        year = entity.releaseYear,
        genre = entity.genre,
        isFavorite = true
    )
}
