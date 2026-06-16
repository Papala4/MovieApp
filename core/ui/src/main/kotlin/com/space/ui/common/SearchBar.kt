package com.space.ui.common

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.ui.theme.MovieTheme
import com.example.ui.R
import com.space.ui.theme.Radius

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    enabled: Boolean = true
) {
    val focusRequester = remember { FocusRequester() }
    val colors = MovieTheme.colors

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(Radius.radius24)
            .background(colors.surface)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(R.drawable.search_btn),
                contentDescription = "Search",
                modifier = Modifier.size(14.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(6.dp))

            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    enabled = enabled,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = colors.textSecondary,
                        fontSize = 14.sp,
                    ),
                    cursorBrush = SolidColor(colors.textSecondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                )

                if (query.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = colors.textSecondary,
                        fontSize = 14.sp,
                        style = MovieTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        query = "",
        onQueryChange = {},
        modifier = Modifier.padding(16.dp)
    )
}