package com.space.moviedetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.moviedetails.contract.MovieDetailsEffect
import com.space.moviedetails.contract.MovieDetailsEvent
import com.space.moviedetails.contract.MovieDetailsState
import com.space.moviedetails.model.MovieDetailsUi
import com.space.moviedetails.presentation.R
import com.space.moviedetails.vm.MovieDetailsViewModel
import com.space.ui.component.navigation_buttons.Header
import com.space.ui.component.state.ErrorState
import com.space.ui.component.state.LoadingState
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val vm: MovieDetailsViewModel = koinViewModel()
    val state by vm.state.collectAsState()

    LaunchedEffect(movieId) {
        vm.onEvent(MovieDetailsEvent.LoadDetails(movieId))
    }

    LaunchedEffect(Unit) {
        vm.effect.collect { effect ->
            when (effect) {
                MovieDetailsEffect.NavigateBack -> onBackClick()
            }
        }
    }

    MovieDetailsContent(
        state = state,
        movieId = movieId,
        modifier = modifier,
        onEvent = vm::onEvent
    )
}

@Composable
private fun MovieDetailsContent(
    state: MovieDetailsState,
    movieId: Int,
    modifier: Modifier = Modifier,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    val colors = MovieTheme.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Header(
            title = stringResource(R.string.movie_details_title),
            onBackClick = { onEvent(MovieDetailsEvent.OnBackClicked) }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when {
                state.isLoading -> {
                    LoadingState()
                }

                state.error != null -> {
                    ErrorState(
                        onRefresh = { onEvent(MovieDetailsEvent.LoadDetails(movieId)) }
                    )
                }

                state.movie != null -> {
                    MovieBodyContent(movie = state.movie, onEvent = onEvent)
                }
            }
        }
    }
}

@Composable
private fun MovieBodyContent(
    movie: MovieDetailsUi,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        MoviePosterSection(
            posterPath = movie.posterPath,
            onTrailerClick = {}
        )

        Spacer(modifier = Modifier.height(Spacing.spacing16))

        MovieTitleRow(
            title = movie.title,
            isFavourite = movie.isFavourite,
            onFavouriteToggle = { onEvent(MovieDetailsEvent.ToggleFavourite) }
        )

        Spacer(modifier = Modifier.height(Spacing.spacing4))

        MovieInfoRow(
            rating = movie.voteAverage,
            genre = movie.genre,
            runtimeMinutes = movie.runtime,
            releaseYear = movie.releaseYear
        )

        Spacer(modifier = Modifier.height(Spacing.spacing26))

        MovieAboutSection(overview = movie.overview)

        Spacer(modifier = Modifier.height(Spacing.spacing32))
    }
}

private val previewMovie = MovieDetailsUi(
    id = 1,
    title = "Atonement",
    overview = "Thirteen-year-old fledgling writer Briony Tallis irrevocably changes the course of several lives when she accuses her older sister's lover of a crime he did not commit.",
    posterPath = "",
    genre = "Romance",
    runtime = "",
    releaseYear = "2007",
    voteAverage = "5.3",
    isFavourite = false
)

@Preview(showBackground = true)
@Composable
private fun MovieDetailsContentPreview() {
    MovieAppTheme {
        MovieDetailsContent(
            state = MovieDetailsState(movie = previewMovie),
            movieId = previewMovie.id,
            onEvent = {}
        )
    }
}