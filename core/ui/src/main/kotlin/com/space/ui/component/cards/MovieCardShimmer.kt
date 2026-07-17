package com.space.ui.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.ui.component.common.shimmerEffect
import com.space.ui.theme.Dimensions
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

/**
 * [MovieCardShimmer] – a loading placeholder matching [MovieCard]'s layout,
 * drawn with the animated [shimmerEffect] background.
 */

@Composable
fun MovieCardShimmer(modifier: Modifier = Modifier) {
    Column(modifier = modifier.width(Dimensions.dimension164)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.spacing220)
                .clip(Radius.radius16)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(Spacing.spacing8))

        Box(
            modifier = Modifier
                .fillMaxWidth(TITLE_LINE_WIDTH_FRACTION)
                .height(Spacing.spacing14)
                .clip(Radius.radius8)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(Spacing.spacing6))

        Box(
            modifier = Modifier
                .fillMaxWidth(YEAR_LINE_WIDTH_FRACTION)
                .height(Spacing.spacing12)
                .clip(Radius.radius8)
                .shimmerEffect()
        )
    }
}

private const val TITLE_LINE_WIDTH_FRACTION = 0.7f
private const val YEAR_LINE_WIDTH_FRACTION = 0.35f

@Preview
@Composable
private fun PreviewMovieCardShimmer() {
    MovieAppTheme {
        Box(modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)) {
            MovieCardShimmer()
        }
    }
}
