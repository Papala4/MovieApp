package com.space.movieapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.space.favorite.screen.FavoriteScreen
import com.space.home.screen.HomeScreen
import com.space.ui.component.navigation_buttons.BottomNavBar

@Composable
fun MovieNavGraph(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeKey)

    fun openHome() {
        backStack.removeAll { it != HomeKey }
    }

    fun openFavorites() {
        if (backStack.lastOrNull() != FavoriteKey) backStack.add(FavoriteKey)
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                isHomeActive = backStack.lastOrNull() == HomeKey,
                onHomeClick = ::openHome,
                onFavoritesClick = ::openFavorites
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<HomeKey> {
                    HomeScreen(onNavigateToFavourites = ::openFavorites)
                }

                entry<FavoriteKey> {
                    FavoriteScreen(onNavigateToHome = ::openHome)
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
