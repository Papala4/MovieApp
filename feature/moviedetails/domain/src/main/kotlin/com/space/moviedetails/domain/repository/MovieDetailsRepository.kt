package com.space.moviedetails.domain.repository

import com.space.moviedetails.domain.model.MovieDetailsDomain
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetailsDomain>>
}
