package com.space.moviedetails.repository

import com.space.moviedetails.model.MovieDetailsResponse
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsResponse>>
}
