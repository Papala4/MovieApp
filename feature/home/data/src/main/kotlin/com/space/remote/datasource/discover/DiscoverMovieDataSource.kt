package com.space.remote.datasource.discover

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

interface DiscoverMovieDataSource {
    suspend fun discoverMovies(genreId: Int, page: Int): Response<PopularMovieDto>
}
