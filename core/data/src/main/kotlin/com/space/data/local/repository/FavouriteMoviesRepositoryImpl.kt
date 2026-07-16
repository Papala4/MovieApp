package com.space.data.local.repository

import com.space.data.local.dao.FavouriteMoviesDao
import com.space.data.local.mapper.FavouriteMovieMapper
import com.space.domain.model.Movie
import com.space.domain.repository.FavouriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteMoviesRepositoryImpl(
    private val dao: FavouriteMoviesDao,
    private val mapper: FavouriteMovieMapper
) : FavouriteMoviesRepository {

    override suspend fun addFavouriteMovie(movie: Movie) =
        dao.insert(mapper.mapToEntity(movie))

    override suspend fun removeFavouriteMovie(movieId: Int) =
        dao.deleteById(movieId)

    override fun getFavouriteMovies(): Flow<List<Movie>> =
        dao.getAll().map { entities -> entities.map(mapper::mapToDomain) }
}
