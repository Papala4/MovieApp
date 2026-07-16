package com.space.remote.datasource.popular

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

interface PopularMovieDataSource {
    suspend fun getPopularMovies(page: Int): Response<PopularMovieDto>
}
