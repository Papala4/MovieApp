package com.space.moviedetails.contract

import com.space.moviedetails.model.MovieDetailsUi
import com.space.presentation.base.UIEffect
import com.space.presentation.base.UIEvent

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: MovieDetailsUi? = null,
    val error: String? = null
)

sealed interface MovieDetailsEvent : UIEvent {
    data class LoadDetails(val movieId: Int) : MovieDetailsEvent
    data object ToggleFavourite : MovieDetailsEvent
    data object OnBackClicked : MovieDetailsEvent
}

sealed interface MovieDetailsEffect : UIEffect {
    data object NavigateBack : MovieDetailsEffect
}
