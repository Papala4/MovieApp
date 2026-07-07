package com.space.domain.usecase

import com.space.domain.repository.FavouriteMoviesRepository

class RemoveFavouriteMovieUseCase(private val repository: FavouriteMoviesRepository) {
    suspend operator fun invoke(movieId: Int) =
        repository.removeFavouriteMovie(movieId)
}
