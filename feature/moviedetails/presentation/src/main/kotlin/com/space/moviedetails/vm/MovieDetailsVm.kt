package com.space.moviedetails.vm

import androidx.lifecycle.viewModelScope
import com.space.moviedetails.contract.MovieDetailsEffect
import com.space.moviedetails.contract.MovieDetailsEvent
import com.space.moviedetails.contract.MovieDetailsState
import com.space.moviedetails.usecase.GetMovieDetailsUseCase
import com.space.network.api_result.ApiResult
import com.space.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<MovieDetailsState, MovieDetailsEvent, MovieDetailsEffect>(MovieDetailsState()) {

    override fun onEvent(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.LoadDetails -> loadDetails(event.movieId)
            MovieDetailsEvent.ToggleFavourite -> toggleFavourite()
            MovieDetailsEvent.OnBackClicked -> sendEffect(MovieDetailsEffect.NavigateBack)
        }
    }

    private fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { result ->
                when (result) {
                    ApiResult.Loading -> setState { copy(isLoading = true, error = null) }
                    is ApiResult.Success -> setState {
                        copy(
                            isLoading = false,
                            movie = result.data
                        )
                    }

                    is ApiResult.Error -> setState {
                        copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    private fun toggleFavourite() {
        setState { copy(movie = movie?.copy(isFavourite = !movie.isFavourite)) }
    }
}
