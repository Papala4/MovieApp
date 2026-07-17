package com.space.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.model.MovieResponse
import com.space.network.exception.ExceptionHandler
import com.space.remote.datasource.discover.DiscoverMovieDataSource
import com.space.remote.mapper.PopularMovieMapper
import com.space.remote.paging.MoviesPagingSource
import com.space.remote.paging.PAGE_SIZE
import com.space.repository.DiscoverMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscoverMovieRepositoryImpl(
    private val dataSource: DiscoverMovieDataSource,
    private val mapper: PopularMovieMapper,
    private val exceptionHandler: ExceptionHandler
) : DiscoverMovieRepository {

    override fun discoverMovies(genreId: Int): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                MoviesPagingSource(exceptionHandler) { page ->
                    dataSource.discoverMovies(genreId, page)
                }
            }
        ).flow.map { pagingData -> pagingData.map(mapper::map) }
}
