package com.space.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: androidx.compose.ui.semantics.Role? = null,
    debounceTime: Long = 500L,
    onClick: () -> Unit
) = composed {
    val debouncedClick = onSingleClick(debounceTime, onClick)
    clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = debouncedClick
    )
}

@Composable
fun onSingleClick(
    debounceTime: Long = 500L,
    onClick: () -> Unit
): () -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

@Composable
fun <T> onSingleClick(
    debounceTime: Long = 500L,
    onClick: (T) -> Unit
): (T) -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return { param ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            onClick(param)
        }
    }
}
