package com.space.favorite.contract

import com.space.presentation.base.UIEffect
import com.space.presentation.base.UIEvent
import com.space.ui.component.cards.Movie

data class FavoriteState(
    val movies: List<Movie> = emptyList()
)

sealed interface FavoriteEvent : UIEvent {
    data class FavouriteToggled(val movieId: Int) : FavoriteEvent
    data object HomeClicked : FavoriteEvent
}

sealed interface FavoriteEffect : UIEffect {
    data object NavigateToHome : FavoriteEffect
}