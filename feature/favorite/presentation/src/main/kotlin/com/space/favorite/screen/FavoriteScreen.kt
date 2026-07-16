package com.space.favorite.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.space.domain.model.Movie
import com.space.favorite.component.FavoriteEmptyState
import com.space.favorite.contract.FavoriteEffect
import com.space.favorite.contract.FavoriteEvent
import com.space.favorite.contract.FavoriteState
import com.space.favorite.presentation.R
import com.space.favorite.vm.FavoriteViewModel
import com.space.ui.component.cards.MovieCard
import com.space.ui.component.navigation_buttons.Header
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing
import com.space.ui.util.onSingleClick
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToDetails: (Int) -> Unit = {}
) {
    val vm: FavoriteViewModel = koinViewModel()
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.effect.collect { effect ->
            when (effect) {
                FavoriteEffect.NavigateToHome -> onNavigateToHome()
                is FavoriteEffect.NavigateToDetails -> onNavigateToDetails(effect.movieId)
            }
        }
    }

    FavoriteContent(
        state = state,
        modifier = modifier,
        onEvent = vm::onEvent
    )
}

@Composable
private fun FavoriteContent(
    state: FavoriteState,
    modifier: Modifier = Modifier,
    onEvent: (FavoriteEvent) -> Unit
) {
    val colors = MovieTheme.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Header(title = stringResource(R.string.favorite_movies_title))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Crossfade(targetState = state.movies.isEmpty()) { isEmpty ->
                if (isEmpty) {
                    FavoriteEmptyState()
                } else {
                    FavoriteMoviesGrid(movies = state.movies, onEvent = onEvent)
                }
            }
        }
    }
}

@Composable
private fun FavoriteMoviesGrid(
    movies: List<Movie>,
    onEvent: (FavoriteEvent) -> Unit
) {
    val debouncedClick = onSingleClick<Movie> { movie ->
        onEvent(FavoriteEvent.MovieClicked(movie))
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing12),
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieCard(
                movie = movie,
                placeholder = painterResource(R.drawable.placeholder),
                onClick = { debouncedClick(it) },
                modifier = Modifier.animateItem(),
                onFavoriteToggle =  { id -> onEvent(FavoriteEvent.FavouriteToggled(id)) }
            )
        }
    }
}