package com.space.ui.component.icons_labels

import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.component.common.BaseIcon

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = isFavourite,
        onCheckedChange = onToggleChange,
        enabled = enabled
    ) {
        BaseIcon(
            icon = if (isFavourite) R.drawable.checked_favourite_btn else R.drawable.favourite_btn
        )
    }
}

@Preview
@Composable
private fun PreviewFavouriteButton() {
    FavouriteButton(
        onToggleChange = {},
        isFavourite = false
    )
}
