package com.space.ui.component.search_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.theme.MovieTheme
import com.space.ui.R
import com.space.ui.theme.Dimensions
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

/**
 * [Search] – a styled, focus-aware search input field.
 *
 * Features a search icon on the left and a text input on the right.
 * - Tapping the icon toggles focus and keyboard visibility.
 * - Clearing all text automatically releases focus and hides the keyboard.
 * - Placeholder is hidden when the field is focused.
 *
 * @param query The current search text (controlled from outside).
 * @param onQueryChange Called on every keystroke with the updated text.
 * @param modifier Optional external modifier for positioning or sizing.
 * @param placeholder Hint text shown when query is empty and field is unfocused. Defaults to "Search".
 * @param enabled Whether the field accepts input. Defaults to true.
*/

@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.search_placeholder),
    enabled: Boolean = true
) {
    val focusRequester = remember { FocusRequester() }
    val colors = MovieTheme.colors
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Spacing.spacing36)
            .clip(Radius.radius24)
            .background(colors.surface)
            .padding(horizontal = Dimensions.dimension24),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(R.drawable.search_btn),
                contentDescription = null,
                modifier = Modifier
                    .size(Dimensions.dimension14)
                    .clickable {
                        if(isFocused) {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        } else {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        }
                    },
                tint = Color.Unspecified

            )

            Spacer(modifier = Modifier.width(Spacing.spacing6))

            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    enabled = enabled,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = colors.textSecondary,
                        fontSize = TextSizing.size14,
                    ),
                    cursorBrush = SolidColor(colors.textSecondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged { state ->
                            isFocused = state.isFocused
                        }
                )

                if (query.isEmpty() && !isFocused) {
                    Text(
                        text = placeholder,
                        color = colors.textSecondary,
                        fontSize = TextSizing.size14,
                        style = MovieTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchPreview() {
    Search(
        query = "",
        onQueryChange = {}
    )
}