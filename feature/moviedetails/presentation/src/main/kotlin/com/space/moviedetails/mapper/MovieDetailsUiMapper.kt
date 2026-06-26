package com.space.moviedetails.mapper

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.model.MovieDetailsUi

class MovieDetailsUiMapper {
    fun map(movieDetailsResponse: MovieDetailsResponse) = MovieDetailsUi(
        id = movieDetailsResponse.id,
        title = movieDetailsResponse.title,
        tagline = movieDetailsResponse.tagline,
        overview = movieDetailsResponse.overview,
        posterPath = movieDetailsResponse.posterPath,
        runtime = movieDetailsResponse.runtime,
        releaseYear = movieDetailsResponse.releaseYear,
        voteAverage = movieDetailsResponse.voteAverage,
        genre = movieDetailsResponse.genre,
    )
}