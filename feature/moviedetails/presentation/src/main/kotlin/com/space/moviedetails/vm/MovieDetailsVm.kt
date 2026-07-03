package com.space.moviedetails.vm

import androidx.lifecycle.viewModelScope
import com.space.moviedetails.contract.MovieDetailsEffect
import com.space.moviedetails.contract.MovieDetailsEvent
import com.space.moviedetails.contract.MovieDetailsState
import com.space.moviedetails.mapper.MovieDetailsUiMapper
import com.space.moviedetails.usecase.GetMovieDetailsUseCase
import com.space.network.api_result.ApiResult
import com.space.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val mapper: MovieDetailsUiMapper
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
                    ApiResult.Loading -> setState { copy(isLoading = true, errorRes = null) }
                    is ApiResult.Success -> setState {
                        copy(
                            isLoading = false,
                            movie = mapper.map(result.data)
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
        setState { copy(movie = movie?.copy(isFavourite = !movie.isFavourite)) }
    }
}