package com.space.remote.datasource

import com.space.network.api_result.ApiResult
import com.space.remote.dto.GenreDto
import kotlinx.coroutines.flow.Flow

interface GenreDataSource {
    fun getGenres(): Flow<ApiResult<List<GenreDto>>>
}
