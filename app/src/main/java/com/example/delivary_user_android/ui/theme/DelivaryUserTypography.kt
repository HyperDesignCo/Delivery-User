package com.example.delivary_user_android.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.delivary_user_android.R

val localDelivaryUserTypography =
    staticCompositionLocalOf<DelivaryUserTypography> { error("Cannot provide text style") }
val cairo = FontFamily(
    Font(R.font.cairo_bold, weight = FontWeight.Bold),
    Font(R.font.cairo_bold, weight = FontWeight.Normal),
)

data class DelivaryUserTypography(
    val headline: SizedTypography,
    val title: SizedTypography,
    val body: SizedTypography,
    val label: SizedTypography,
)

data class SizedTypography(
    val extraLarge: TextStyle,
    val large: TextStyle,
    val medium: TextStyle,
    val small: TextStyle,
    val extraSmall: TextStyle,
)

private val headline = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 32.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    large = TextStyle(
        fontSize = 20.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    medium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    small = TextStyle(
        fontSize = 18.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    extraSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    )
)

private val title = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    large = TextStyle(
        fontSize = 14.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    medium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    extraSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    )
)

private val body = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 20.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    large = TextStyle(
        fontSize = 16.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    medium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    small = TextStyle(
        fontSize = 14.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    ),
    extraSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 14.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    )
)

private val label = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 12.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    ),
    large = TextStyle(
        fontSize = 12.sp,
        lineHeight = 25.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    ),
    medium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    ),
    small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    ),
    extraSmall = TextStyle(
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    )
)
val cairoTypography = DelivaryUserTypography(
    headline = headline,
    title = title,
    body = body,
    label = label
)