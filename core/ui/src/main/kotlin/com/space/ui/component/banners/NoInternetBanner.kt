package com.space.ui.component.banners

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

/**
 * [NoInternetBanner] – app-level banner shown while the device has no internet connection.
 *
 * Designed to sit at the very top of the screen, above the navigation host:
 * its background extends behind the status bar in edge-to-edge mode.
 *
 * @param visible Whether the banner is shown; changes are animated.
 * @param modifier Optional external modifier for sizing / spacing.
 */
@Composable
fun NoInternetBanner(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.error)
                .statusBarsPadding()
                .padding(vertical = Spacing.spacing8),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_internet_connection),
                color = colors.textPrimary,
                fontSize = TextSizing.size12,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNoInternetBanner() {
    MovieAppTheme {
        NoInternetBanner(visible = true)
    }
}
