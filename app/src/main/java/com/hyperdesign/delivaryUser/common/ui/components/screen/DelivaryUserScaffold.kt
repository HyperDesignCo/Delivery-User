package com.hyperdesign.delivaryUser.common.ui.components.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp

val LocalScaffoldInnerPadding = compositionLocalOf { PaddingValues() }

object DelivaryUserScaffold {
    val innerPadding: PaddingValues
        @Composable @ReadOnlyComposable get() = LocalScaffoldInnerPadding.current

    @Composable
    fun calculateStartPadding(): Dp {
        val layoutDirection = LocalLayoutDirection.current
        return innerPadding.calculateStartPadding(layoutDirection)
    }

    @Composable
    fun calculateEndPadding(): Dp {
        val layoutDirection = LocalLayoutDirection.current
        return innerPadding.calculateEndPadding(layoutDirection)
    }
}