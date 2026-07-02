package com.space.ui.component.icons_labels

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.ui.R
import com.space.ui.component.common.BaseIcon

@Composable
fun CancelButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(36.dp),
        enabled = enabled
    ) {
        BaseIcon(icon = R.drawable.cancel_btn)
    }
}

@Preview
@Composable
private fun PreviewCancelButton() {
    CancelButton(
        onClick = {}
    )
}
