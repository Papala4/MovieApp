package com.space.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.space.home.contract.HomeEvent
import com.space.home.presentation.R
import com.space.ui.component.cards.Movie
import com.space.ui.component.cards.MovieCard
import com.space.ui.component.cards.MovieCardShimmer
import com.space.ui.theme.Spacing

@Composable
fun MoviesGrid(
    movies: LazyPagingItems<Movie>,
    onEvent: (HomeEvent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COLUMNS),
        contentPadding = PaddingValues(Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = movies.itemCount) { index ->
            movies[index]?.let { movie ->
                MovieCard(
                    movie = movie,
                    onFavoriteToggle = { onEvent(HomeEvent.FavouriteToggled(it.id)) },
                    placeholder = painterResource(R.drawable.placeholder)
                )
            }
        }

        if (movies.loadState.append is LoadState.Loading) {
            items(GRID_COLUMNS) {
                MovieCardShimmer()
            }
        }
    }
}

@Composable
fun MoviesGridShimmer() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COLUMNS),
        contentPadding = PaddingValues(Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize()
    ) {
        items(SHIMMER_CARD_COUNT) {
            MovieCardShimmer()
        }
    }
}

private const val GRID_COLUMNS = 2
private const val SHIMMER_CARD_COUNT = 6