package com.space.remote.api

import com.space.remote.dto.GenresDto
import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {
    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresDto>
}