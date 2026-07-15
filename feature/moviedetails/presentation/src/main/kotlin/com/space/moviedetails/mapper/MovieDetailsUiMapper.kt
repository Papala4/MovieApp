package com.space.moviedetails.mapper

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

    private fun formatRuntime(minutes: Int): String {
        val h = minutes / 60
        val m = minutes % 60
        return if (h > 0) "${h}h ${m}m" else "${m}m"
    }
}