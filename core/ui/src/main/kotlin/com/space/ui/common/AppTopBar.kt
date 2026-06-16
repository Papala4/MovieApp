package com.space.ui.common

import com.example.ui.R
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
import androidx.compose.ui.unit.dp
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun AppTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    val colors = MovieTheme.colors

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = Spacing.spacing16)
    ) {

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(R.drawable.back_btn),
                contentDescription = null,
                tint = colors.textPrimary
            )
        }

        Text(
            text = title,
            style = MovieTheme.typography.titleMedium,
            color = colors.textPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewAppTopBar() {
    AppTopBar(
        title = "Test",
        onBackClick = {}
    )
}