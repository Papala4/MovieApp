package com.space.ui.component.search_filter

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
import com.space.ui.theme.Dimensions
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

/**
 * [CategoryControl] – a horizontal pill-style tab/filter selector.
 *
 * Renders a row of tappable labels where exactly one item is "selected" at a time.
 * The selected item is visually highlighted with a filled background, while
 * unselected items show a bordered outline style.
 *
 * @param items The list of category labels to display (e.g. ["All", "Action", "Drama"]).
 * @param selected The currently selected label — must match one of the [items] values.
 * @param modifier Optional external modifier for positioning or sizing.
 * @param onItemClick Called with the label string when the user taps a category.
 *
 *
 * */

@Composable
fun CategoryControl(
    items: List<String>,
    selected: String,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
) {
    val colors = MovieTheme.colors

    Row(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = Radius.radius30
            )
            .padding(Spacing.spacing4),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing8)
    ) {
        items.forEach { item ->

            val isSelected = item == selected

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(Spacing.spacing22)
                    .wrapContentWidth()
                    .clip(Radius.radius30)
                    .background(
                        color = if (isSelected) colors.primary else colors.onPrimary
                    )
                    .then(
                        if (!isSelected) Modifier.border(
                            width = Dimensions.dimension01,
                            color = colors.border,
                            shape = Radius.radius30
                        ) else Modifier
                    )
                    .clickable { onItemClick(item) }
                    .padding(horizontal = Spacing.spacing20)
            ) {

            Text(
                    text = item,
                    style = MovieTheme.typography.bodySmall,
                    color = if (isSelected) colors.onPrimary else colors.textPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryControl() {
    CategoryControl(
        items = listOf("Test1", "Test2"),
        selected = "Test1",
        onItemClick = {}
    )
}