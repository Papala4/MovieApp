package com.space.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.R

@Composable
fun FavouriteButton(
    onToggleChange: (Boolean) -> Unit,
    isFavourite: Boolean,
    enabled: Boolean = true,
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
fun PreviewFavouriteButton() {
    FavouriteButton(
        onToggleChange = {},
        isFavourite = false
    )
}