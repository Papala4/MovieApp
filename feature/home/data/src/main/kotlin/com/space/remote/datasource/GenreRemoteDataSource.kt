package com.space.remote.datasource

import com.space.network.api_result.ApiResult
import com.space.network.handler.ResponseHandler
import com.space.remote.api.GenreApi
import com.space.remote.dto.GenreDto
import kotlinx.coroutines.flow.Flow

class GenreDataSourceImpl(
    private val api: GenreApi,
    private val responseHandler: ResponseHandler
) : GenreDataSource {

    override fun getGenres(): Flow<ApiResult<List<GenreDto>>> =
        responseHandler.apiCall { api.getGenres() }
}
