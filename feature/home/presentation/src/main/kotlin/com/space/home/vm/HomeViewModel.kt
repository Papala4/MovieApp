package com.space.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.domain.usecase.AddFavouriteMovieUseCase
import com.space.domain.usecase.GetFavouriteMoviesUseCase
import com.space.domain.usecase.RemoveFavouriteMovieUseCase
import com.space.home.contract.HomeEffect
import com.space.home.contract.HomeEvent
import com.space.home.contract.HomeState
import com.space.home.mapper.MovieUiMapper
import com.space.model.GenreResponse
import com.space.model.MovieResponse
import com.space.network.api_result.ApiResult
import com.space.presentation.base.BaseViewModel
import com.space.ui.component.cards.Movie
import com.space.usecase.DiscoverMoviesUseCase
import com.space.usecase.GetGenresUseCase
import com.space.usecase.GetPopularMoviesUseCase
import com.space.usecase.SearchMoviesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    getGenresUseCase: GetGenresUseCase,
    getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val mapper: MovieUiMapper
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>(HomeState()) {

    private val refreshTrigger = MutableStateFlow(0)

    private val favouriteIds: Flow<Set<Int>> = getFavouriteMoviesUseCase()
        .map { favourites -> favourites.map { it.id }.toSet() }
        .distinctUntilChanged()

    private val popularMovies: Flow<PagingData<MovieResponse>> =
        getPopularMoviesUseCase().cachedIn(viewModelScope)

    private val loadedGenres: Flow<List<GenreResponse>> = refreshTrigger
        .flatMapLatest { getGenresUseCase() }
        .onEach(::handleGenresResult)
        .mapNotNull { result ->
            when (result) {
                ApiResult.Loading -> null
                is ApiResult.Success -> result.data
                is ApiResult.Error -> emptyList()
            }
        }

    @OptIn(FlowPreview::class)
    private val debouncedQuery: Flow<String> = state
        .map { it.query }
        .distinctUntilChanged()
        .debounce { query -> if (query.isEmpty()) 0L else 300L }

    private val categoryFilter: Flow<String> = state
        .map { it.selectedCategory }
        .distinctUntilChanged()

    private val filters: Flow<MovieFilters> = combine(
        debouncedQuery,
        categoryFilter
    ) { query, category -> MovieFilters(query = query, category = category) }

    val movies: Flow<PagingData<Movie>> = combine(
        filters,
        loadedGenres
    ) { movieFilters, genreList -> movieFilters to genreList }
        .distinctUntilChanged()
        .flatMapLatest { (movieFilters, genreList) ->
            val genreNames = genreList.associate { it.id to it.name }

            moviesSource(movieFilters, genreList).map { pagingData ->
                pagingData.map { movie -> mapper.map(movie, genreNames, isFavourite = false) }
            }
        }
        .cachedIn(viewModelScope)
        .combine(favouriteIds) { pagingData, favourites ->
            pagingData.map { movie -> movie.copy(isFavorite = movie.id in favourites) }
        }

    private fun moviesSource(
        movieFilters: MovieFilters,
        genreList: List<GenreResponse>
    ): Flow<PagingData<MovieResponse>> {
        val genreId = genreList.firstOrNull { it.name == movieFilters.category }?.id
        return when {
            movieFilters.query.isNotBlank() -> searchMoviesUseCase(movieFilters.query.trim())
            genreId != null -> discoverMoviesUseCase(genreId)
            else -> popularMovies
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.QueryChanged -> setState { copy(query = event.query) }
            is HomeEvent.FilterToggled -> setState { copy(isFilterSelected = event.isSelected) }
            is HomeEvent.CategorySelected -> setState {
                copy(
                    selectedCategory = if (selectedCategory == event.category) "" else event.category
                )
            }
            is HomeEvent.FavouriteToggled -> toggleFavourite(event.movie)

            HomeEvent.Refresh -> refreshTrigger.update { it + 1 }
            HomeEvent.FavouritesClicked -> sendEffect(HomeEffect.NavigateToFavourites)
        }
    }

    private fun toggleFavourite(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                removeFavouriteMovieUseCase(movie.id)
            } else {
                addFavouriteMovieUseCase(mapper.mapToFavourite(movie))
            }
        }
    }

    private fun handleGenresResult(result: ApiResult<List<GenreResponse>>) {
        when (result) {
            ApiResult.Loading -> setState { copy(isLoading = true, error = null) }
            is ApiResult.Success -> setState {
                copy(
                    isLoading = false,
                    error = null,
                    categories = result.data.map { it.name }
                )
            }

            is ApiResult.Error -> setState {
                copy(
                    isLoading = false,
                    error = result.exception.messageRes
                )
            }
        }
    }

    private data class MovieFilters(
        val query: String,
        val category: String
    )
}