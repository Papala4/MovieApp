package com.space.moviedetails.remote.datasource

import com.space.moviedetails.remote.api.MovieDetailsApi
import com.space.moviedetails.remote.dto.MovieDetailsDto
import com.space.network.api_result.ApiResult
import com.space.network.handler.ResponseHandler
import kotlinx.coroutines.flow.Flow

class MovieDetailsDataSourceImpl(
    private val api: MovieDetailsApi,
    private val responseHandler: ResponseHandler
) : MovieDetailsDataSource {

    override fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsDto>> =
        responseHandler.apiCall { api.getMovieDetails(movieId) }
}
