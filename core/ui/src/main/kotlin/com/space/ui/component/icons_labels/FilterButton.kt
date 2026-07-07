package com.space.ui.component.icons_labels

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.component.common.BaseIcon
import com.space.ui.theme.Size

@Composable
fun FilterButton(
    isSelected: Boolean,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = isSelected,
        onCheckedChange = onToggleChange,
        modifier = Modifier.size(Size.size36),
        enabled = enabled
    ) {
        BaseIcon(
            icon = if (isSelected) R.drawable.selected_filter_btn else R.drawable.filter_btn
        )
    }
}

@Preview
@Composable
private fun PreviewFilterButton() {
    FilterButton(
        onToggleChange = {},
        isSelected = true
    )
}