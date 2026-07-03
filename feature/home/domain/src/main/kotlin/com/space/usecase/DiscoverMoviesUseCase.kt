package com.space.usecase

import androidx.paging.PagingData
import com.space.model.MovieResponse
import com.space.repository.DiscoverMovieRepository
import kotlinx.coroutines.flow.Flow

class DiscoverMoviesUseCase(private val repository: DiscoverMovieRepository) {
    operator fun invoke(genreId: Int): Flow<PagingData<MovieResponse>> =
        repository.discoverMovies(genreId)
}
