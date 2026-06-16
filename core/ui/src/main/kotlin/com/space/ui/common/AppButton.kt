package com.space.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AppButtonStyle = AppButtonStyle.Primary,
    icon: ImageVector? = null
) {
    val colors = MovieTheme.colors

    val backgroundColor = when (style) {
        AppButtonStyle.Primary -> colors.primary
        AppButtonStyle.Secondary -> colors.surface
    }

    val contentColor = when (style) {
        AppButtonStyle.Primary -> colors.onPrimary
        AppButtonStyle.Secondary -> colors.textPrimary
    }

    Button(
        onClick = onClick,
        shape = Radius.radius30,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = modifier
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(Spacing.spacing8))
        }

        Text(
            text = text,
            style = MovieTheme.typography.labelLarge
        )
    }
}

enum class AppButtonStyle {
    Primary,
    Secondary
}

@Preview
@Composable
fun AppButtonPreview() {
    AppButton(
        "Test",
        onClick = {},
        style = AppButtonStyle.Primary
    )
}