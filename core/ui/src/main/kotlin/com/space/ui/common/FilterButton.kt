package com.space.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import com.example.ui.R
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilterButton(
    onToggleChange: (Boolean) -> Unit,
    isSelected: Boolean,
    enabled: Boolean = true,
) {
        IconToggleButton(
            checked = isSelected,
            onCheckedChange = onToggleChange,
            modifier = Modifier.size(36.dp),
            enabled = enabled
            ) {
            Icon(
                painter = painterResource(
                    if (isSelected) R.drawable.selected_filter_btn else R.drawable.filter_btn
                ),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

}


@Preview
@Composable
fun PreviewFilterButton() {
    FilterButton(
        onToggleChange = {},
        isSelected = true
    )
}