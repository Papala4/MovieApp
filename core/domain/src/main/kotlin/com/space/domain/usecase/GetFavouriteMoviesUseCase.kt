package com.space.domain.usecase

import com.space.domain.model.FavouriteMovie
import com.space.domain.repository.FavouriteMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteMoviesUseCase(private val repository: FavouriteMoviesRepository) {
    operator fun invoke(): Flow<List<FavouriteMovie>> =
        repository.getFavouriteMovies()
}
