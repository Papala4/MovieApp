package com.space.home.mapper

import com.space.domain.model.FavouriteMovie
import com.space.model.MovieResponse
import com.space.ui.component.cards.Movie

class MovieUiMapper {
    fun map(
        movie: MovieResponse,
        genreNames: Map<Int, String>,
        isFavourite: Boolean
    ) = Movie(
        id = movie.id,
        title = movie.title,
        year = movie.releaseYear,
        genre = movie.genreIds.firstNotNullOfOrNull { genreNames[it] }.orEmpty(),
        posterUrl = movie.posterPath,
        isFavorite = isFavourite
    )

    fun mapToFavourite(movie: Movie) = FavouriteMovie(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterUrl,
        releaseYear = movie.year,
        genre = movie.genre
    )
}
