package com.space.home.mapper

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
}
