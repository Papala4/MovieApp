package com.space.ui.component.state

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.component.common.BaseIcon
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = FULL_ROTATION,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ROTATION_DURATION, easing = LinearEasing)
        ),
        label = "loadingRotation"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BaseIcon(
            icon = R.drawable.loader_icon,
            modifier = Modifier.rotate(angle)
        )
    }
}

private const val FULL_ROTATION = 360f
private const val ROTATION_DURATION = 1000

@Preview
@Composable
private fun PreviewLoadingState() {
    MovieAppTheme {
        LoadingState(
            modifier = Modifier.background(colors.background)
        )
    }
}
