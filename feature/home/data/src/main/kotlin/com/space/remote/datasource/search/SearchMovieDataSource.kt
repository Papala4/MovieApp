package com.space.remote.datasource.search

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response

interface SearchMovieDataSource {
    suspend fun searchMovies(query: String, page: Int): Response<PopularMovieDto>
}
