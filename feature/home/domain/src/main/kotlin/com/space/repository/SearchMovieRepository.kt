package com.space.repository

import androidx.paging.PagingData
import com.space.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {
    fun searchMovies(query: String): Flow<PagingData<MovieResponse>>
}
