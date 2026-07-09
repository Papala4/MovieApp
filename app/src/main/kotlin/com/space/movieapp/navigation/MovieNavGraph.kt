package com.space.movieapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.space.favorite.screen.FavoriteScreen
import com.space.home.screen.HomeScreen
import com.space.moviedetails.screen.MovieDetailsScreen
import com.space.ui.component.navigation_buttons.BottomNavBar
import com.space.ui.theme.MovieTheme

@Composable
fun MovieNavGraph(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeKey)
    val currentKey = backStack.lastOrNull()

    fun openHome() {
        backStack.removeAll { it != HomeKey }
    }

    fun openFavorites() {
        backStack.removeAll { it !is HomeKey && it !is FavoriteKey }
        if (backStack.lastOrNull() != FavoriteKey) backStack.add(FavoriteKey)
    }

    fun openDetails(movieId: Int) {
        backStack.add(MovieDetailsKey(movieId))
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (currentKey !is MovieDetailsKey) {
                BottomNavBar(
                    isHomeActive = currentKey == HomeKey,
                    onHomeClick = ::openHome,
                    onFavoritesClick = ::openFavorites,
                    modifier = Modifier.background(MovieTheme.colors.background)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<HomeKey> {
                    HomeScreen(
                        onNavigateToFavourites = ::openFavorites,
                        onNavigateToDetails = ::openDetails
                    )
                }

                entry<FavoriteKey> {
                    FavoriteScreen(
                        onNavigateToHome = ::openHome,
                        onNavigateToDetails = ::openDetails
                    )
                }

                entry<MovieDetailsKey> { key ->
                    MovieDetailsScreen(
                        movieId = key.movieId,
                        onBackClick = { if (backStack.size > 1) backStack.removeLastOrNull() }
                    )
                }
            },
            modifier = Modifier.padding(
                start = innerPadding.calculateStartPadding(layoutDirection),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(layoutDirection)
            )
        )
    }
}
