package com.space.remote.repository

import com.space.model.GenreResponse
import com.space.network.api_result.ApiResult
import com.space.remote.datasource.genre.GenreDataSource
import com.space.remote.mapper.GenreMapper
import com.space.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenreRepositoryImpl(
    private val dataSource: GenreDataSource,
    private val mapper: GenreMapper
) : GenreRepository {

    override fun getGenres(): Flow<ApiResult<List<GenreResponse>>> =
        dataSource.getGenres().map { result ->
            when (result) {
                is ApiResult.Success -> ApiResult.Success(result.data.genres.map(mapper::map))
                is ApiResult.Error -> ApiResult.Error(result.exception)
                ApiResult.Loading -> ApiResult.Loading
            }
        }
}
