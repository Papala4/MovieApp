package com.example.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class MovieAppColors(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val border: Color,
    val error: Color,
    val success: Color,
    val warning: Color,
    val overlay: Color
)

val YellowPrimary   = Color(0xFFFFC44A)
val Neutral01Black  = Color(0xFF080808)
val Neutral02       = Color(0xFF1C1C1C)
val Neutral03       = Color(0xFF5D5D5D)
val Neutral04       = Color(0xFF808080)
val Neutral05       = Color(0xFFA5A5A5)
val Neutral06       = Color(0xFFCACACA)
val Neutral07       = Color(0xFFDEDEDE)
val Neutral08       = Color(0xFFEAEAEA)

val DarkMovieColors = MovieAppColors(
    primary = YellowPrimary,
    background = Neutral01Black,
    surface = Neutral02,
    surfaceVariant = Neutral03,
    onPrimary = Neutral01Black,
    onBackground = Neutral08,
    onSurface = Neutral08,
    textPrimary = Neutral08,
    textSecondary = Neutral05,
    border = Neutral03,
    error = Color(0xFFCF6679),
    success = Color(0xFF4CAF50),
    warning = YellowPrimary,
    overlay = Color(0x99000000)
)

val LocalMovieColors = staticCompositionLocalOf { DarkMovieColors }