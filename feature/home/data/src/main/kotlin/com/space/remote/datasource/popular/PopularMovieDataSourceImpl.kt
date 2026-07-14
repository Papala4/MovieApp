package com.space.remote.datasource.popular

import com.space.remote.api.PopularMovieApi
import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

class PopularMovieDataSourceImpl(
    private val api: PopularMovieApi
) : PopularMovieDataSource {

    override suspend fun getPopularMovies(page: Int): Response<PopularMovieDto> =
        api.getPopularMovies(page)
}
