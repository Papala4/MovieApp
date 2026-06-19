package com.space.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.space.ui.theme.Dimensions
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val posterUrl: String,
    val isFavorite: Boolean = false
)

/**
 * [MovieCard] – a self-contained, reusable card component.
 *
 * @param movie        The movie data to display.
 * @param onFavoriteToggle  Called when the user taps the heart icon.
 * @param modifier     Optional external modifier for sizing / spacing.
 */
@Composable
fun MovieCard(
    movie: Movie,
    onFavoriteToggle: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors

    Card(
        modifier = modifier
            .width(Dimensions.dimension164),
        shape = Radius.radius16,
        colors = CardDefaults.cardColors(containerColor = colors.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.dimension06)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Spacing.spacing220)
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(Radius.radius16)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Spacing.spacing60)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, colors.surface)
                            )
                        )
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
                    .padding(horizontal = Spacing.spacing10, vertical = Spacing.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = movie.title,
                        color = colors.textPrimary,
                        fontSize = TextSizing.size12,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(Spacing.spacing2))
                    Text(
                        text = movie.year.toString(),
                        color = colors.textSecondary,
                        fontSize = TextSizing.size10
                    )
                }

                FavouriteButton(
                    isFavourite = movie.isFavorite,
                    enabled = true,
                    onToggleChange = { onFavoriteToggle(movie) }
                )
            }
        }
    }
}

@Composable
fun GenreBadge(genre: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = colors.primary, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = Spacing.spacing10, vertical = Spacing.spacing4)
    ) {
        Text(
            text = genre,
            color = colors.surface,
            fontSize = TextSizing.size10,
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
fun PreviewMovieCard() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MovieCard(
                movie = Movie(
                    id = 1,
                    year = 2019,
                    title = "Test1",
                    genre = "SCI-FI",
                    posterUrl = "https://petapixel.com/assets/uploads/2024/01/High-resolution-image-of-sun-1536x806.jpg"
                ),
                onFavoriteToggle = {}
            )
        }
    }
}
