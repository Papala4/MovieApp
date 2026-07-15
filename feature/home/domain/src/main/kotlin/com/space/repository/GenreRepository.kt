package com.space.repository

import com.space.model.GenreResponse
import com.space.network.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getGenres(): Flow<ApiResult<List<GenreResponse>>>
}
