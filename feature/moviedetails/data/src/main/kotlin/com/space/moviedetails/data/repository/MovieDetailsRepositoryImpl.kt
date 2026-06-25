package com.space.moviedetails.data.repository

import com.space.moviedetails.data.mapper.toDomain
import com.space.moviedetails.data.remote.api.MovieDetailsApi
import com.space.moviedetails.domain.model.MovieDetailsDomain
import com.space.moviedetails.domain.repository.MovieDetailsRepository
import com.space.network.api_result.ApiResult
import com.space.network.handler.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl(
    private val api: MovieDetailsApi,
    private val responseHandler: ResponseHandler
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsDomain>> =
        responseHandler.apiCall { api.getMovieDetails(movieId) }
            .map { result ->
                when (result) {
                    is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
                    is ApiResult.Error -> ApiResult.Error(result.message)
                    ApiResult.Loading -> ApiResult.Loading
                }
            }
}
