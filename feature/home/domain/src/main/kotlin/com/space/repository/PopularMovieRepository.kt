package com.space.repository

import androidx.paging.PagingData
import com.space.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieResponse>>
}
