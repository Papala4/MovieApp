package com.space.moviedetails.remote.api

import com.space.moviedetails.remote.dto.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsDto>
}
