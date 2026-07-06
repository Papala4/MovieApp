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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
 *   cancel button that clears the text (via [onQueryChange] with an empty string)
 *   and releases focus / hides the keyboard, so the query state stays hoisted
 *   at the caller.
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
    isFilterSelected: Boolean,
    categories: List<String>,
    selectedCategory: String,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onFilterToggle: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    onClick = {
                        onQueryChange("")
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
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


@Preview
@Composable
private fun PreviewSearchBarWithCategories() {
    MovieAppTheme {
        SearchBar(
            query = "",
            onQueryChange = {},
            isFilterSelected = true,
            onFilterToggle = {},
            categories = listOf("Comedy", "Drama", "Romance", "Horror", "Science Fiction"),
            selectedCategory = "Comedy",
            onCategoryClick = {},
            modifier = Modifier
                .background(colors.background)
                .padding(Spacing.spacing16)
        )
    }
}
