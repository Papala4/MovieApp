package com.space.ui.component.common

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun BaseIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int
    ) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = modifier,
        tint = Color.Unspecified
    )
}