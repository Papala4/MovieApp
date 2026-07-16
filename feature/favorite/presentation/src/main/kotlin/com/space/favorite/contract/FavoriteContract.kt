package com.space.favorite.contract

import com.space.domain.model.Movie
import com.space.presentation.base.UIEffect
import com.space.presentation.base.UIEvent

data class FavoriteState(
    val movies: List<Movie> = emptyList()
)

sealed interface FavoriteEvent : UIEvent {
    data class MovieClicked(val movie: Movie) : FavoriteEvent
    data class FavouriteToggled(val movieId: Int) : FavoriteEvent
    data object HomeClicked : FavoriteEvent
}

sealed interface FavoriteEffect : UIEffect {
    data object NavigateToHome : FavoriteEffect
    data class NavigateToDetails(val movieId: Int) : FavoriteEffect
}