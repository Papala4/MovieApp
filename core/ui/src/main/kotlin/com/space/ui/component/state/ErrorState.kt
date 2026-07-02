package com.space.ui.component.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.R
import com.space.ui.component.common.BaseIcon
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun ErrorState(
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseIcon(icon = R.drawable.data_load_icon)

        Spacer(modifier = Modifier.height(Spacing.spacing26))

        Text(
            text = stringResource(R.string.error_state_title),
            style = typography.titleSmall,
            color = colors.textPrimary
        )

        Spacer(modifier = Modifier.height(Spacing.spacing12))

        Text(
            text = stringResource(R.string.error_state_description),
            style = typography.bodyMedium,
            color = colors.textSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = Spacing.spacing64)
        )

        Spacer(modifier = Modifier.height(Spacing.spacing64))

        Button(
            onClick = onRefresh,
            shape = Radius.radius24,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            ),
            contentPadding = PaddingValues(
                horizontal = Spacing.spacing32,
                vertical = Spacing.spacing12
            )
        ) {
            Text(
                text = stringResource(R.string.error_state_refresh),
                style = typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(Spacing.spacing6))
            BaseIcon(icon = R.drawable.refresh_icon)
        }
    }
}

@Preview
@Composable
private fun PreviewErrorState() {
    MovieAppTheme {
        ErrorState(
            onRefresh = {},
            modifier = Modifier.background(colors.background)
        )
    }
}
