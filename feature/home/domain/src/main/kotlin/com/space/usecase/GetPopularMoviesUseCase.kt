package com.space.usecase

import androidx.paging.PagingData
import com.space.model.MovieResponse
import com.space.repository.PopularMovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(private val repository: PopularMovieRepository) {
    operator fun invoke(): Flow<PagingData<MovieResponse>> =
        repository.getPopularMovies()
}
