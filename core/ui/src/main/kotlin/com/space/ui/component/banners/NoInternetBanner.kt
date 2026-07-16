package com.space.ui.component.banners

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Spacing

/**
 * [NoInternetBanner] – app-level banner shown while the device has no internet connection.
 *
 * Works at either screen edge in edge-to-edge mode: the background extends behind the
 * system bar given via [windowInsets], while the text stays clear of it.
 *
 * @param visible Whether the banner is shown; changes are animated.
 * @param modifier Optional external modifier for sizing / spacing.
 * @param message Text shown inside the banner.
 * @param windowInsets System bar insets the banner is placed against:
 * [WindowInsets.Companion.statusBars] for top placement,
 * [WindowInsets.Companion.navigationBars] for bottom placement,
 * or `WindowInsets(0)` when it sits next to a component that already handles insets,
 * such as a bottom navigation bar.
 */
@Composable
fun NoInternetBanner(
    visible: Boolean,
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.no_internet_connection),
    windowInsets: WindowInsets = WindowInsets(0)
) {
    val typography = MovieTheme.typography

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.background)
                .windowInsetsPadding(windowInsets)
                .padding(vertical = Spacing.spacing8),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = colors.primary,
                style = typography.labelMedium,
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