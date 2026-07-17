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
import com.space.network.api_result.ApiResult
import com.space.network.network_observer.NetworkObserver
import com.space.presentation.base.BaseViewModel
import com.space.ui.component.cards.Movie
import com.space.usecase.DiscoverMoviesUseCase
import com.space.usecase.GetGenresUseCase
import com.space.usecase.GetPopularMoviesUseCase
import com.space.usecase.SearchMoviesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeVm(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val mapper: MovieUiMapper,
    networkObserver: NetworkObserver
    getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val mapper: MovieUiMapper
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>(HomeState()) {

    val isOnline: StateFlow<Boolean> = networkObserver.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(ONLINE_SUBSCRIBE_TIMEOUT),
            initialValue = true
        )

    private val refreshTrigger = MutableStateFlow(0)

    private val favouriteIds: Flow<Set<Int>> = getFavouriteMoviesUseCase()
        .map { favourites -> favourites.map { it.id }.toSet() }
        .distinctUntilChanged()

    init {
        setState { copy(movies = createMoviesFlow()) }
    }

    private fun createMoviesFlow(): Flow<PagingData<Movie>> {
        val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)

        val loadedGenres = refreshTrigger
            .flatMapLatest { getGenresUseCase() }
            .onEach(::handleGenresResult)
            .mapNotNull { result ->
                when (result) {
                    is ApiResult.Success -> result.data
                    is ApiResult.Error -> emptyList()
                    else -> null
                }
            }

        val filters = combine(
            state.map { it.query }.distinctUntilChanged()
                .debounce { if (it.isEmpty()) DEBOUNCE_EMPTY else DEBOUNCE_SEARCH },
            state.map { it.selectedCategory }.distinctUntilChanged()
        ) { query, category -> MovieFilters(query, category) }

        return combine(filters, loadedGenres) { f, genres -> f to genres }
            .distinctUntilChanged()
            .flatMapLatest { (f, genres) ->
                val genreNames = genres.associate { it.id to it.name }
                val genreId = genres.firstOrNull { it.name == f.category }?.id

                val source = when {
                    f.query.isNotBlank() -> searchMoviesUseCase(f.query.trim())
                    genreId != null -> discoverMoviesUseCase(genreId)
                    else -> popularMovies
                }

                source.map { pagingData ->
                    pagingData.map { mapper.map(it, genreNames, isFavourite = false) }
                }
            }
            .cachedIn(viewModelScope)
            .combine(favouriteIds) { pagingData, favourites ->
                pagingData.map { it.copy(isFavorite = it.id in favourites) }
            }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.QueryChanged -> setState { copy(query = event.query) }
            is HomeEvent.FilterToggled -> setState { copy(isFilterSelected = event.isSelected) }
            is HomeEvent.CategorySelected -> setState {
                copy(selectedCategory = if (selectedCategory == event.category) "" else event.category)
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
                copy(isLoading = false, error = null, categories = result.data.map { it.name })
            }
            is ApiResult.Error -> setState {
                copy(isLoading = false, error = result.exception.messageRes)
            }
        }
    }

    private data class MovieFilters(val query: String, val category: String)

    companion object {
        private const val DEBOUNCE_SEARCH = 300L
        private const val DEBOUNCE_EMPTY = 0L
        private const val ONLINE_SUBSCRIBE_TIMEOUT = 5_000L
    }
}
