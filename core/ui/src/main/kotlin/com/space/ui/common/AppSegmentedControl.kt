package com.space.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun AppSegmentedControl(
    items: List<String>,
    selected: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors

    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing8),
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = Radius.radius30
            )
            .padding(Spacing.spacing4)
    ) {
        items.forEach { item ->

            val isSelected = item == selected

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(21.dp)
                    .wrapContentWidth()
                    .clip(Radius.radius30)
                    .background(
                        if (isSelected) colors.primary else colors.onPrimary
                    )
                    .then(
                        if (!isSelected) Modifier.border(
                            width = 1.dp,
                            color = colors.textPrimary,
                            shape = Radius.radius30
                        ) else Modifier
                    )
                    .clickable { onItemClick(item) }
                    .padding(horizontal = Spacing.spacing20)
            ) {

            Text(
                    text = item,
                    style = MovieTheme.typography.labelSmall,
                    color = if (isSelected)
                        colors.onPrimary
                    else
                        colors.textPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAppSegmentedControl() {
    AppSegmentedControl(
        items = listOf("Test1", "Test2"),
        selected = "Test1",
        onItemClick = {}
    )
}