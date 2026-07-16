package com.space.moviedetails.vm

import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.AddFavouriteMovieUseCase
import com.space.domain.usecase.GetFavouriteMoviesUseCase
import com.space.domain.usecase.RemoveFavouriteMovieUseCase
import com.space.moviedetails.contract.MovieDetailsEffect
import com.space.moviedetails.contract.MovieDetailsEvent
import com.space.moviedetails.contract.MovieDetailsState
import com.space.moviedetails.mapper.MovieDetailsUiMapper
import com.space.moviedetails.usecase.GetMovieDetailsUseCase
import com.space.network.api_result.ApiResult
import com.space.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieDetailsVm(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val mapper: MovieDetailsUiMapper
) : BaseViewModel<MovieDetailsState, MovieDetailsEvent, MovieDetailsEffect>(MovieDetailsState()) {

    private val favouriteIds: Flow<Set<Int>> = getFavouriteMoviesUseCase()
        .map { favourites -> favourites.map { it.id }.toSet() }
        .distinctUntilChanged()

    override fun onEvent(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.LoadDetails -> loadDetails(event.movieId)
            MovieDetailsEvent.ToggleFavourite -> toggleFavourite()
            MovieDetailsEvent.OnBackClicked -> sendEffect(MovieDetailsEffect.NavigateBack)
        }
    }

    private fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId)
                .combine(favouriteIds) { result, favourites -> result to favourites }
                .collect { (result, favourites) ->
                    when (result) {
                        ApiResult.Loading -> setState { copy(isLoading = true, errorRes = null) }
                        is ApiResult.Success -> setState {
                            copy(
                                isLoading = false,
                                movie = mapper.map(result.data)
                                    .copy(isFavourite = result.data.id in favourites)
                            )
                        }

                        is ApiResult.Error -> setState {
                            copy(
                                isLoading = false,
                                errorRes = result.exception.messageRes
                            )
                        }
                    }
                }
        }
    }

    private fun toggleFavourite() {
        val movie = state.value.movie ?: return
        viewModelScope.launch {
            if (movie.isFavourite) {
                removeFavouriteMovieUseCase(movie.id)
            } else {
                addFavouriteMovieUseCase(mapper.mapToMovie(movie))
            }
        }
    }
}
