package com.space.moviedetails.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.space.moviedetails.presentation.R
import com.space.ui.component.common.BaseIcon
import com.space.ui.component.icons_labels.FavouriteButton
import com.space.ui.theme.Dimensions
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun MoviePosterSection(
    posterPath: String,
    onTrailerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors

    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.76f)
        )

        Button(
            onClick = onTrailerClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Spacing.spacing16),
            shape = Radius.radius24,
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            contentPadding = PaddingValues(
                horizontal = Spacing.spacing20,
                vertical = Spacing.spacing10
            )
        ) {
            Text(
                text = stringResource(R.string.movie_details_trailer),
                color = colors.onPrimary,
                style = MovieTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.padding(Spacing.spacing4))

            BaseIcon(
                icon = R.drawable.trailer_button
            )
        }
    }
}

@Composable
fun MovieTitleRow(
    title: String,
    isFavourite: Boolean,
    onFavouriteToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.spacing16),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MovieTheme.typography.titleMedium,
            color = MovieTheme.colors.textPrimary,
            modifier = Modifier.weight(1f)
        )
        FavouriteButton(
            isFavourite = isFavourite,
            onToggleChange = onFavouriteToggle
        )
    }
}

@Composable
fun MovieInfoRow(
    rating: String,
    genre: String?,
    runtimeMinutes: String,
    releaseYear: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing6),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoChip(
            icon = R.drawable.star_logo,
            text = rating
        )

        if (!genre.isNullOrBlank()) {
            InfoChip(text = genre)
        }

        InfoChip(
            icon = R.drawable.clock_logo,
            text = runtimeMinutes
        )

        InfoChip(text = releaseYear)
    }
}

@Composable
private fun InfoChip(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null
) {
    Row(
        modifier = modifier
            .background(color = MovieTheme.colors.surface, shape = Radius.radius22)
            .padding(Spacing.spacing10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing4)
    ) {
        if (icon != null) {
            BaseIcon(
                icon = icon,
                modifier = Modifier.size(Dimensions.dimension14)
            )
        }
        Text(
            text = text,
            style = MovieTheme.typography.bodyMedium,
            color = MovieTheme.colors.textSecondary
        )
    }
}

@Composable
fun MovieAboutSection(
    overview: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.spacing16),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing8)
    ) {
        Text(
            text = stringResource(R.string.movie_details_about),
            style = MovieTheme.typography.titleMedium,
            color = MovieTheme.colors.textPrimary
        )
        Text(
            text = overview,
            style = MovieTheme.typography.bodyMedium,
            color = MovieTheme.colors.textSecondary
        )
    }
}

