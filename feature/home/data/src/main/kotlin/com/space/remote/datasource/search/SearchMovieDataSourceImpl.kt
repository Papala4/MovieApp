package com.space.remote.datasource.search

import com.space.remote.api.SearchMovieApi
import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

class SearchMovieDataSourceImpl(
    private val api: SearchMovieApi
) : SearchMovieDataSource {

    override suspend fun searchMovies(query: String, page: Int): Response<PopularMovieDto> =
        api.searchMovies(query, page)
}
