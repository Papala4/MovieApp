package com.space.ui.component.icons_labels

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Size
import com.space.ui.util.onSingleClick

@Composable
fun CancelButton(
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onSingleClick { onClick() },
        enabled = enabled,
        modifier = Modifier.height(Size.size36),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MovieTheme.colors.textSecondary
        )
    ) {
        Text(
            text = stringResource(R.string.search_cancel),
            style = MovieTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun PreviewCancelButton() {
    CancelButton(
        onClick = {}
    )
}
