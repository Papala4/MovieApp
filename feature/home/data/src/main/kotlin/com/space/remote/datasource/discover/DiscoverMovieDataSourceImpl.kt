package com.space.remote.datasource.discover

import com.space.remote.api.DiscoverMovieApi
import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

class DiscoverMovieDataSourceImpl(
    private val api: DiscoverMovieApi
) : DiscoverMovieDataSource {

    override suspend fun discoverMovies(genreId: Int, page: Int): Response<PopularMovieDto> =
        api.discoverMovies(genreId, page)
}
