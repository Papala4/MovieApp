package com.space.favorite.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.space.favorite.presentation.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun FavoriteEmptyState(
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .offset(y = -Spacing.spacing96),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.empty_result_icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(Spacing.spacing20))

        Text(
            text = stringResource(R.string.favorite_empty_message),
            style = MovieTheme.typography.bodyMedium,
            color = colors.border,
            textAlign = TextAlign.Center
        )
    }
}