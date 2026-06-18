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

private val YellowPrimary = Color(0xFFFFC44A)
private val Neutral01 = Color(0xFF080808)
private val Neutral02 = Color(0xFF1C1C1C)
private val Neutral03 = Color(0xFF5D5D5D)
private val Neutral04 = Color(0xFF808080)
private val Neutral05 = Color(0xFFA5A5A5)
private val Neutral06 = Color(0xFFCACACA)
private val Neutral07 = Color(0xFFDEDEDE)
private val Neutral08 = Color(0xFFEAEAEA)
private val WarningColor = Color(0xFFFFEB3B)
private val SuccessColor = Color(0xFF4CAF50)
private val ErrorColor = Color(0xFFFC0000)


val DarkMovieColors = MovieAppColors(
    primary = YellowPrimary,
    background = Neutral01,
    surface = Neutral02,
    surfaceVariant = Neutral03,
    onPrimary = Neutral01,
    onBackground = Neutral08,
    onSurface = Neutral07,
    textPrimary = Neutral08,
    textSecondary = Neutral06,
    border = Neutral04,
    overlay = Neutral05,
    error = ErrorColor,
    success = SuccessColor,
    warning = WarningColor,
)

val LocalMovieColors = staticCompositionLocalOf { DarkMovieColors }