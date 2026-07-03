package com.space.ui.component.icons_labels

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.theme.MovieTheme

@Composable
fun CancelButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
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
