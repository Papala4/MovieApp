package com.space.usecase

import androidx.paging.PagingData
import com.space.model.MovieResponse
import com.space.repository.SearchMovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val repository: SearchMovieRepository) {
    operator fun invoke(query: String): Flow<PagingData<MovieResponse>> =
        repository.searchMovies(query)
}
