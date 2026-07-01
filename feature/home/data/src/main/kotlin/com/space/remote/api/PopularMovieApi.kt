package com.space.remote.api

import com.space.remote.dto.PopularMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<PopularMovieDto>
}