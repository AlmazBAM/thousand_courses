package com.bagmanovam.thousand_courses.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bagmanovam.thousand_courses.R

val manrope = FontFamily(
    Font(
        resId = R.font.manrope_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.manrope_bold,
        weight = FontWeight.Bold
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Normal,
    ),
    titleLarge   = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Bold,
    ),
    labelLarge = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Bold,
    ),
    labelMedium = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Medium,
    ),
    labelSmall = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Light,
    )
)