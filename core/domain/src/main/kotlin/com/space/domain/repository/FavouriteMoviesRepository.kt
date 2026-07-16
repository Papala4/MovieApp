package com.space.domain.repository

import com.space.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavouriteMoviesRepository {
    suspend fun addFavouriteMovie(movie: Movie)
    suspend fun removeFavouriteMovie(movieId: Int)
    fun getFavouriteMovies(): Flow<List<Movie>>
}
