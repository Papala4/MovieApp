package com.space.domain.usecase

import com.space.domain.model.FavouriteMovie
import com.space.domain.repository.FavouriteMoviesRepository

class AddFavouriteMovieUseCase(private val repository: FavouriteMoviesRepository) {
    suspend operator fun invoke(movie: FavouriteMovie) =
        repository.addFavouriteMovie(movie)
}
