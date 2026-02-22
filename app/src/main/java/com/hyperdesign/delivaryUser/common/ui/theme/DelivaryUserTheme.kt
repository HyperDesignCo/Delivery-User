package com.hyperdesign.delivaryUser.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun DelivaryUserTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) delivaryUserDarkColors else delivaryUserLightColors
    CompositionLocalProvider(
        LocalDelivaryUserColors provides colors,
        localDelivaryUserTypography provides cairoTypography
    ) {
        content()
    }
}

object DelivaryUserTheme {
    val colors: DelivaryUserColors
        @Composable
        @ReadOnlyComposable get() = LocalDelivaryUserColors.current
    val typography: DelivaryUserTypography
        @Composable @ReadOnlyComposable get() = localDelivaryUserTypography.current
}