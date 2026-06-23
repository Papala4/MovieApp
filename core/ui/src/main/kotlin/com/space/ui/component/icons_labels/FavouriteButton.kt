package com.space.ui.component.icons_labels

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R

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
        Icon(
            painter = painterResource(
                if(isFavourite) R.drawable.checked_favourite_btn else R.drawable.favourite_btn
            ),
            contentDescription = null,
            tint = Color.Unspecified
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
