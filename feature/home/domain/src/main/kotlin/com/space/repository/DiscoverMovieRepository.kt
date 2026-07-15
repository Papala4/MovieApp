package com.space.repository

import androidx.paging.PagingData
import com.space.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {
    fun discoverMovies(genreId: Int): Flow<PagingData<MovieResponse>>
}
