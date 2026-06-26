package com.space.moviedetails.remote.datasource

import com.space.moviedetails.remote.dto.MovieDetailsDto
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

interface MovieDetailsDataSource {
    fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsDto>>
}
