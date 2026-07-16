package com.space.remote.datasource.genre

import com.space.network.api_result.ApiResult
import com.space.remote.dto.GenresDto
import kotlinx.coroutines.flow.Flow

interface GenreDataSource {
    fun getGenres(): Flow<ApiResult<GenresDto>>
}