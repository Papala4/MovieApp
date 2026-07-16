package com.space.ui.component.navigation_buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun BottomNavBar(
    isHomeActive: Boolean,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors

    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing8),
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = Spacing.spacing16, vertical = Spacing.spacing8)
            .background(color = colors.onPrimary, shape = Radius.radius12)
            .padding(Spacing.spacing8)
    ) {
        NavigationButton(
            text = stringResource(R.string.nav_home),
            active = isHomeActive,
            icon = R.drawable.home_btn,
            onClick = onHomeClick,
            modifier = Modifier.weight(1f)
        )

        NavigationButton(
            text = stringResource(R.string.nav_favorites),
            active = !isHomeActive,
            icon = R.drawable.favorite_nav_btn,
            onClick = onFavoritesClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
private fun BottomNavBarPreview() {
    BottomNavBar(
        isHomeActive = true,
        onHomeClick = {},
        onFavoritesClick = {}
    )
}
