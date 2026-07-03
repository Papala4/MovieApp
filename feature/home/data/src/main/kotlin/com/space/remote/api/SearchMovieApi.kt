package com.space.remote.api

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieApi {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<PopularMovieDto>
}
