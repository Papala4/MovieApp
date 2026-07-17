package com.space.domain.repository

import com.space.domain.model.FavouriteMovie
import kotlinx.coroutines.flow.Flow

interface FavouriteMoviesRepository {
    suspend fun addFavouriteMovie(movie: FavouriteMovie)
    suspend fun removeFavouriteMovie(movieId: Int)
    fun getFavouriteMovies(): Flow<List<FavouriteMovie>>
}
