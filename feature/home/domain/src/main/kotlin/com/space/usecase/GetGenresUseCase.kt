package com.space.usecase

import com.space.model.GenreResponse
import com.space.network.api_result.ApiResult
import com.space.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val repository: GenreRepository) {
    operator fun invoke(): Flow<ApiResult<List<GenreResponse>>> =
        repository.getGenres()
}
