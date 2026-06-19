package com.space.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun NavButton(
    text: String,
    modifier: Modifier = Modifier,
    style: NavButtonStyle = NavButtonStyle.Primary,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    val colors = MovieTheme.colors

    val backgroundColor = when (style) {
        NavButtonStyle.Primary -> colors.primary
        NavButtonStyle.Secondary -> colors.surface
    }

    val contentColor = when (style) {
        NavButtonStyle.Primary -> colors.onPrimary
        NavButtonStyle.Secondary -> colors.textPrimary
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
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(Spacing.spacing8))

        Text(
            text = text,
            style = MovieTheme.typography.bodyMedium
        )
    }
}

enum class NavButtonStyle {
    Primary,
    Secondary
}

@Preview
@Composable
fun NavButtonPreview() {
    NavButton(
        "Test",
        style = NavButtonStyle.Primary,
        icon = R.drawable.search_btn,
        onClick = {}
    )
}