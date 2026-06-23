package com.space.ui.component.icons_labels

import androidx.compose.foundation.layout.size
import com.space.ui.R
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.ui.component.common.BaseIcon

@Composable
fun FilterButton(
    isSelected: Boolean,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = isSelected,
        onCheckedChange = onToggleChange,
        modifier = Modifier.size(36.dp),
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