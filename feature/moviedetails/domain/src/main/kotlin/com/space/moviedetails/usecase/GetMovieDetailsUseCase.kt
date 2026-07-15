package com.space.moviedetails.usecase

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.repository.MovieDetailsRepository
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val repository: MovieDetailsRepository) {
    operator fun invoke(movieId: Int): Flow<ApiResult<MovieDetailsResponse>> =
        repository.getMovieDetails(movieId)
}
