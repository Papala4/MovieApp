package com.space.favorite.vm

import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.AddFavouriteMovieUseCase
import com.space.domain.usecase.GetFavouriteMoviesUseCase
import com.space.domain.usecase.RemoveFavouriteMovieUseCase
import com.space.favorite.contract.FavoriteEffect
import com.space.favorite.contract.FavoriteEvent
import com.space.favorite.contract.FavoriteState
import com.space.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavoriteViewModel(
    getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase
) : BaseViewModel<FavoriteState, FavoriteEvent, FavoriteEffect>(FavoriteState()) {

    init {
        getFavouriteMoviesUseCase()
            .onEach { favourites ->
                setState { copy(movies = favourites) }
            }
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.FavouriteToggled -> toggleFavourite(event.movieId)
            is FavoriteEvent.MovieClicked -> sendEffect(FavoriteEffect.NavigateToDetails(event.movie.id))
            FavoriteEvent.HomeClicked -> sendEffect(FavoriteEffect.NavigateToHome)
        }
    }

    private fun toggleFavourite(movieId: Int) {
        val movie = state.value.movies.find { it.id == movieId } ?: return
        viewModelScope.launch {
            if (movie.isFavorite) {
                removeFavouriteMovieUseCase(movie.id)
            } else {
                addFavouriteMovieUseCase(movie)
            }
        }
    }
}
