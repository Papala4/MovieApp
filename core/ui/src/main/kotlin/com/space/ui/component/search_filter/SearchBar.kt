package com.space.ui.component.search_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.component.icons_labels.CancelButton
import com.space.ui.component.icons_labels.FilterButton
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Spacing

/**
 * [SearchBar] – a composite search component combining [Search], [FilterButton],
 * [CancelButton] and [CategoryControl].
 *
 * - While the query is empty the filter toggle is shown next to the field.
 * - As soon as the user types something the filter toggle is replaced by a
 *   cancel button that clears the text (via [onQueryChange] with an empty string),
 *   so the query state stays hoisted at the caller.
 * - When the filter is selected, a horizontally scrollable [CategoryControl]
 *   appears below the search field.
 *
 * @param query The current search text (controlled from outside).
 * @param onQueryChange Called on every keystroke and with "" when cancel is tapped.
 * @param isFilterSelected Whether the filter toggle is checked.
 * @param onFilterToggle Called when the user taps the filter toggle.
 * @param categories The category labels shown when the filter is selected.
 * @param selectedCategory The currently selected category label.
 * @param onCategoryClick Called with the label when the user taps a category.
 * @param modifier Optional external modifier for positioning or sizing.
 */

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    isFilterSelected: Boolean,
    onFilterToggle: (Boolean) -> Unit,
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Search(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(Spacing.spacing8))

            if (query.isEmpty()) {
                FilterButton(
                    isSelected = isFilterSelected,
                    onToggleChange = onFilterToggle
                )
            } else {
                CancelButton(
                    onClick = { onQueryChange("") }
                )
            }
        }

        if (isFilterSelected) {
            Spacer(modifier = Modifier.height(Spacing.spacing8))

            CategoryControl(
                items = categories,
                selected = selectedCategory,
                onItemClick = onCategoryClick,
                modifier = Modifier.horizontalScroll(rememberScrollState())
            )
        }
    }
}

private val previewCategories =
    listOf("Comedy", "Drama", "Romance", "Horror", "Science Fiction")

@Preview
@Composable
private fun PreviewSearchBar() {
    MovieAppTheme {
        var query by remember { mutableStateOf("") }
        var isFilterSelected by remember { mutableStateOf(false) }
        var selectedCategory by remember { mutableStateOf(previewCategories.first()) }

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            isFilterSelected = isFilterSelected,
            onFilterToggle = { isFilterSelected = it },
            categories = previewCategories,
            selectedCategory = selectedCategory,
            onCategoryClick = { selectedCategory = it },
            modifier = Modifier
                .background(colors.background)
                .padding(Spacing.spacing16)
        )
    }
}

@Preview
@Composable
private fun PreviewSearchBarWithCategories() {
    MovieAppTheme {
        SearchBar(
            query = "",
            onQueryChange = {},
            isFilterSelected = true,
            onFilterToggle = {},
            categories = previewCategories,
            selectedCategory = previewCategories.first(),
            onCategoryClick = {},
            modifier = Modifier
                .background(colors.background)
                .padding(Spacing.spacing16)
        )
    }
}

@Preview
@Composable
private fun PreviewSearchBarTyping() {
    MovieAppTheme {
        SearchBar(
            query = "Interstellar",
            onQueryChange = {},
            isFilterSelected = false,
            onFilterToggle = {},
            categories = previewCategories,
            selectedCategory = previewCategories.first(),
            onCategoryClick = {},
            modifier = Modifier
                .background(colors.background)
                .padding(Spacing.spacing16)
        )
    }
}
