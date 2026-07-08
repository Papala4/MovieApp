package com.space.favorite.mapper

import com.space.domain.model.FavouriteMovie
import com.space.ui.component.cards.Movie

class FavouriteMovieUiMapper {
    fun map(movie: FavouriteMovie) = Movie(
        id = movie.id,
        title = movie.title,
        year = movie.releaseYear,
        genre = movie.genre,
        posterUrl = movie.posterUrl,
        isFavorite = true
    )

    fun mapToFavourite(movie: Movie) = FavouriteMovie(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterUrl,
        releaseYear = movie.year,
        genre = movie.genre
    )
}