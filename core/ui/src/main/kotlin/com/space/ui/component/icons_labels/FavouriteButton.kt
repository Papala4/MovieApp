package com.space.ui.component.icons_labels

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.space.ui.R
import com.space.ui.component.common.BaseIcon
import com.space.ui.theme.Dimensions
import com.space.ui.util.onSingleClick

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    enabled: Boolean = true,
    size: Dp = Dimensions.dimension40,
    onToggleChange: (Boolean) -> Unit
) {
    val debouncedToggle = onSingleClick { onToggleChange(!isFavourite) }

    IconToggleButton(
        checked = isFavourite,
        onCheckedChange = { debouncedToggle() },
        enabled = enabled,
        modifier = Modifier.size(size)
    ) {
        BaseIcon(
            icon = if (isFavourite) R.drawable.checked_favourite_btn else R.drawable.favourite_btn,
            modifier = Modifier.size(size * ICON_SIZE_RATIO)
        )
    }
}

private const val ICON_SIZE_RATIO = 0.80f

@Preview
@Composable
private fun PreviewFavouriteButton() {
    FavouriteButton(
        onToggleChange = {},
        isFavourite = false
    )
}
