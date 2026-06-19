package com.space.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import com.space.ui.R
import com.space.ui.theme.TextSizing as Size


val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

data class MovieAppTypography(
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle
)

val MovieTypography = MovieAppTypography(
    titleMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = Size.size20,
        lineHeight = Size.size26
    ),
    titleSmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = Size.size16,
        lineHeight = Size.size18
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = Size.size14,
        lineHeight = Size.size18
    ),
    bodySmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = Size.size10,
        lineHeight = Size.size14
    )
)

val LocalMovieTypography = staticCompositionLocalOf { MovieTypography }