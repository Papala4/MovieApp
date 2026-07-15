package com.space.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.model.MovieResponse
import com.space.remote.datasource.search.SearchMovieDataSource
import com.space.remote.mapper.PopularMovieMapper
import com.space.remote.paging.MoviesPagingSource
import com.space.remote.paging.PAGE_SIZE
import com.space.repository.SearchMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchMovieRepositoryImpl(
    private val dataSource: SearchMovieDataSource,
    private val mapper: PopularMovieMapper
) : SearchMovieRepository {

    override fun searchMovies(query: String): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                MoviesPagingSource { page -> dataSource.searchMovies(query, page) }
            }
        ).flow.map { pagingData -> pagingData.map(mapper::map) }
}
