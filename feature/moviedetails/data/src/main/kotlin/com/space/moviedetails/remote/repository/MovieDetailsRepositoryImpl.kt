package com.space.moviedetails.remote.repository

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.moviedetails.remote.datasource.MovieDetailsDataSource
import com.space.moviedetails.remote.mapper.MovieDetailsMapper
import com.space.moviedetails.repository.MovieDetailsRepository
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl(
    private val dataSource: MovieDetailsDataSource,
    private val mapper: MovieDetailsMapper
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsResponse>> =
        dataSource.getMovieDetails(movieId).map { result ->
            when (result) {
                is ApiResult.Success -> ApiResult.Success(mapper.map(result.data))
                is ApiResult.Error -> ApiResult.Error(result.exception)
                ApiResult.Loading -> ApiResult.Loading
            }
        }
}
