package com.space.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.space.domain.model.Movie
import com.space.home.contract.HomeEvent
import com.space.home.presentation.R
import com.space.network.exception.BaseException
import com.space.network.exception.ErrorCode
import com.space.ui.component.banners.NoInternetBanner
import com.space.ui.component.cards.MovieCard
import com.space.ui.component.cards.MovieCardShimmer
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
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
                    onFavoriteToggle = { _ -> onEvent(HomeEvent.FavouriteToggled(movie)) },
                    placeholder = painterResource(R.drawable.placeholder)
                )
            }
        }

        when (val appendState = movies.loadState.append) {
            is LoadState.Loading -> {
                items(GRID_COLUMNS) {
                    MovieCardShimmer()
                }
            }

            is LoadState.Error -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    val error = appendState.error
                    if (error is BaseException && error.code == ErrorCode.NETWORK) {
                        NoInternetBanner(visible = true)
                    } else {
                        AppendErrorItem(onRetry = { movies.retry() })
                    }
                }
            }

            is LoadState.NotLoading -> {}
        }
    }
}

@Composable
fun AppendErrorItem(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.spacing16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(com.space.ui.R.string.error_loading_more),
            style = MovieTheme.typography.bodyMedium,
            color = MovieTheme.colors.textSecondary
        )
        Spacer(modifier = Modifier.height(Spacing.spacing8))
        Button(
            onClick = onRetry,
            shape = Radius.radius24,
            colors = ButtonDefaults.buttonColors(
                containerColor = MovieTheme.colors.primary,
                contentColor = MovieTheme.colors.onPrimary
            )
        ) {
            Text(
                text = stringResource(com.space.ui.R.string.retry),
                style = MovieTheme.typography.bodyMedium
            )
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