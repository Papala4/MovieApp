package com.space.home.contract

import androidx.annotation.StringRes
import com.space.presentation.base.UIEffect
import com.space.presentation.base.UIEvent
import com.space.ui.component.cards.Movie

data class HomeState(
    val query: String = "",
    val isFilterSelected: Boolean = false,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    @param:StringRes val error: Int? = null
)

sealed interface HomeEvent : UIEvent {
    data class QueryChanged(val query: String) : HomeEvent
    data class FilterToggled(val isSelected: Boolean) : HomeEvent
    data class CategorySelected(val category: String) : HomeEvent
    data class FavouriteToggled(val movie: Movie) : HomeEvent
    data object Refresh : HomeEvent
    data object FavouritesClicked : HomeEvent
}

sealed interface HomeEffect : UIEffect {
    data object NavigateToFavourites : HomeEffect
}
