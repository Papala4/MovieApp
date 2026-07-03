package com.space.ui.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.space.ui.component.icons_labels.FavouriteButton
import com.space.ui.theme.Dimensions
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val posterUrl: String,
    val isFavorite: Boolean = false
)

/**
 * [MovieCard] – a self-contained, reusable card component.
 *
 * @param movie The movie data to display.
 * @param onFavoriteToggle Called when the user taps the heart icon.
 * @param modifier Optional external modifier for sizing / spacing.
 * @param placeholder Painter shown while the poster loads or fails to load.
 */

@Composable
fun MovieCard(
    movie: Movie,
    onFavoriteToggle: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null
) {

    Card(
        modifier = modifier
            .width(Dimensions.dimension164),
        shape = Radius.radius16,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Spacing.spacing250)
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = placeholder,
                    error = placeholder,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(Radius.radius16),

                )

                GenreBadge(
                    genre = movie.genre,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Spacing.spacing10)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Spacing.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = movie.title,
                        color = colors.textPrimary,
                        fontSize = TextSizing.size14,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = movie.year,
                        color = colors.textSecondary,
                        fontSize = TextSizing.size12
                    )
                }

                FavouriteButton(
                    isFavourite = movie.isFavorite,
                    onToggleChange = { onFavoriteToggle(movie) }
                )
            }
        }
    }
}

@Composable
private fun GenreBadge(genre: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = colors.primary, shape = Radius.radius24)
            .padding(horizontal = Spacing.spacing10, vertical = Spacing.spacing4)
    ) {
        Text(
            text = genre,
            color = colors.surface,
            fontSize = TextSizing.size12,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun MovieRow(
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    var movieList by remember { mutableStateOf(movies) }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing12)
    ) {
        items(movieList, key = { it.id }) { movie ->
            MovieCard(
                movie = movie,
                onFavoriteToggle = { toggled ->
                    movieList = movieList.map {
                        if (it.id == toggled.id) it.copy(isFavorite = !it.isFavorite) else it
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMovieCard() {
    MovieAppTheme {
        Box(modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)) {
            MovieCard(
                movie = Movie(
                    id = 1,
                    year = "2019",
                    title = "Test1",
                    genre = "Adventure",
                    posterUrl = ""
                ),
                onFavoriteToggle = {}
            )
        }
    }
}