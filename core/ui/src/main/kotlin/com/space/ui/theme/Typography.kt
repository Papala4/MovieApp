package com.space.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.space.ui.R

val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

data class MovieAppTypography(
    val labelSmall: TextStyle,
    val labelMedium: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val bodyMedium: TextStyle,
    val headlineSmall: TextStyle
)

val MovieTypography = MovieAppTypography(
    labelSmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSizing.size10,
        lineHeight = TextSizing.size13,
    ),
    labelMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizing.size12,
        lineHeight = TextSizing.size16,
    ),
    titleLarge = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = TextSizing.size20,
        lineHeight = TextSizing.size26
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = TextSizing.size16,
        lineHeight = TextSizing.size20
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = TextSizing.size14,
        lineHeight = TextSizing.size18
    ),
    headlineSmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = TextSizing.size18,
        lineHeight = TextSizing.size18
    )
)

val LocalMovieTypography = staticCompositionLocalOf { MovieTypography }