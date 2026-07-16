package com.space.home.contract

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.space.domain.model.Movie
import com.space.presentation.base.UIEffect
import com.space.presentation.base.UIEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val query: String = "",
    val isFilterSelected: Boolean = false,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    @param:StringRes val error: Int? = null,
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)

sealed interface HomeEvent : UIEvent {
    data class QueryChanged(val query: String) : HomeEvent
    data class FilterToggled(val isSelected: Boolean) : HomeEvent
    data class CategorySelected(val category: String) : HomeEvent
    data class MovieClicked(val movie: Movie) : HomeEvent
    data class FavouriteToggled(val movie: Movie) : HomeEvent
    data object Refresh : HomeEvent
    data object FavouritesClicked : HomeEvent
}

sealed interface HomeEffect : UIEffect {
    data object NavigateToFavourites : HomeEffect
    data class NavigateToDetails(val movieId: Int) : HomeEffect
}
