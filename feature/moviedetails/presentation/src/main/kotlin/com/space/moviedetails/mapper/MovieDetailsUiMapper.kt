package com.space.moviedetails.mapper

import com.space.domain.model.Movie
import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.model.MovieDetailsUi
import java.util.Locale

class MovieDetailsUiMapper {
    fun map(movieDetailsResponse: MovieDetailsResponse) = MovieDetailsUi(
        id = movieDetailsResponse.id,
        title = movieDetailsResponse.title,
        overview = movieDetailsResponse.overview,
        posterPath = movieDetailsResponse.posterPath,
        runtime = formatRuntime(movieDetailsResponse.runtime),
        releaseYear = movieDetailsResponse.releaseYear,
        voteAverage = String.format(Locale.US, "%.1f", movieDetailsResponse.voteAverage),
        genre = movieDetailsResponse.genre,
    )

    fun mapToMovie(movie: MovieDetailsUi) = Movie(
        id = movie.id,
        title = movie.title,
        posterUrl = movie.posterPath,
        year = movie.releaseYear,
        genre = movie.genre.orEmpty(),
        isFavorite = movie.isFavourite
    )

    private fun formatRuntime(minutes: Int): String {
        val h = minutes / 60
        val m = minutes % 60
        return if (h > 0) "${h}h ${m}m" else "${m}m"
    }
}
