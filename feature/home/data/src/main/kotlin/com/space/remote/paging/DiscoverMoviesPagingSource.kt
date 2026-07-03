package com.space.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.remote.api.DiscoverMovieApi
import com.space.remote.dto.MovieDto
import retrofit2.HttpException

class DiscoverMoviesPagingSource(
    private val api: DiscoverMovieApi,
    private val genreId: Int
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = api.discoverMovies(genreId, page)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                LoadResult.Page(
                    data = body.results,
                    prevKey = if (page == STARTING_PAGE) null else page - 1,
                    nextKey = if (page < body.totalPages) page + 1 else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }

    companion object {
        private const val STARTING_PAGE = 1
    }
}
