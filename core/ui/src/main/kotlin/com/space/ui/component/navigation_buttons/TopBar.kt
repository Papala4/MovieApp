package com.space.ui.component.navigation_buttons

import com.space.ui.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun Header(
    title: String,
    onBackClick: (() -> Unit)? = null
) {
    val colors = MovieTheme.colors

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Spacing.spacing56)
            .padding(horizontal = Spacing.spacing16),
        contentAlignment = Alignment.CenterStart
    ) {

        if(onBackClick != null) BackButton(onBackClick)

        Text(
            text = title,
            style = MovieTheme.typography.titleSmall,
            color = colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BackButton(
    onBackClick: () -> Unit
) {
    val colors = MovieTheme.colors

    IconButton(
        onClick = onBackClick,
    ) {
        Icon(
            painter = painterResource(R.drawable.back_btn),
            contentDescription = null,
            tint = colors.textPrimary
        )
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    Header(
        title = "Test",
        onBackClick = {}
    )
}