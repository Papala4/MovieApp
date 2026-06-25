package com.space.moviedetails.domain.usecase

import com.space.moviedetails.domain.model.MovieDetailsDomain
import com.space.moviedetails.domain.repository.MovieDetailsRepository
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val repository: MovieDetailsRepository) {
    operator fun invoke(movieId: Int): Flow<ApiResult<MovieDetailsDomain>> =
        repository.getMovieDetails(movieId)
}
