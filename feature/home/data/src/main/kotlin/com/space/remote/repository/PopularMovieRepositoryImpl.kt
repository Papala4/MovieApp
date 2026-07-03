package com.space.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.model.MovieResponse
import com.space.remote.api.PopularMovieApi
import com.space.remote.mapper.PopularMovieMapper
import com.space.remote.paging.MoviesPagingSource
import com.space.repository.PopularMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularMovieRepositoryImpl(
    private val api: PopularMovieApi,
    private val mapper: PopularMovieMapper
) : PopularMovieRepository {

    override fun getPopularMovies(): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { MoviesPagingSource(api::getPopularMovies) }
        ).flow.map { pagingData -> pagingData.map(mapper::map) }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
