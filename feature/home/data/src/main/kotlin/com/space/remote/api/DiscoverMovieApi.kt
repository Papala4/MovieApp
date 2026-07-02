package com.space.remote.api

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverMovieApi {
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") withGenres: String,
        @Query("page") page: Int
    ): Response<PopularMovieDto>
}
