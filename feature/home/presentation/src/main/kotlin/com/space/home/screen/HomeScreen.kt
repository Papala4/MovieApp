package com.space.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.space.home.contract.HomeEffect
import com.space.home.contract.HomeEvent
import com.space.home.contract.HomeState
import com.space.home.presentation.R
import com.space.home.vm.HomeViewModel
import com.space.ui.component.cards.Movie
import com.space.ui.component.search_filter.SearchBar
import com.space.ui.component.state.EmptyState
import com.space.ui.component.state.ErrorState
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToFavourites: () -> Unit = {},
    onNavigateToDetails: (Int) -> Unit = {}
) {
    val vm: HomeViewModel = koinViewModel()
    val state by vm.state.collectAsState()
    val movies = state.movies.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        vm.effect.collect { effect ->
            when (effect) {
                HomeEffect.NavigateToFavourites -> onNavigateToFavourites()
                is HomeEffect.NavigateToDetails -> onNavigateToDetails(effect.movieId)
            }
        }
    }

    HomeContent(
        state = state,
        movies = movies,
        modifier = modifier,
        onEvent = vm::onEvent
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    movies: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit
) {
    val colors = MovieTheme.colors
    val allCategory = stringResource(R.string.home_category_all)
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
    ) {
        SearchBar(
            query = state.query,
            onQueryChange = { onEvent(HomeEvent.QueryChanged(it)) },
            isFilterSelected = state.isFilterSelected,
            onFilterToggle = { onEvent(HomeEvent.FilterToggled(it)) },
            categories = listOf(allCategory) + state.categories,
            selectedCategory = state.selectedCategory.ifEmpty { allCategory },
            onCategoryClick = { category ->
                onEvent(
                    HomeEvent.CategorySelected(if (category == allCategory) "" else category)
                )
            },
            modifier = Modifier.padding(top = Spacing.spacing16)
        )

        Spacer(modifier = Modifier.height(Spacing.spacing16))

        Text(
            text = stringResource(R.string.home_movies_title),
            style = MovieTheme.typography.titleMedium,
            color = colors.primary,
            modifier = Modifier.padding(horizontal = Spacing.spacing16)
        )

        Spacer(modifier = Modifier.height(Spacing.spacing4))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            val refreshState = movies.loadState.refresh
            when {
                state.isLoading || refreshState is LoadState.Loading -> MoviesGridShimmer()

                state.error != null || refreshState is LoadState.Error -> ErrorState(
                    onRefresh = {
                        onEvent(HomeEvent.Refresh)
                        movies.retry()
                    }
                )

                state.query.isNotBlank() && movies.itemCount == 0 -> EmptyState()

                else -> MoviesGrid(movies = movies, onEvent = onEvent)
            }
        }

    }
}