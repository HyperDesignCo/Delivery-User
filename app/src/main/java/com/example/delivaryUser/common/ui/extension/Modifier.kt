package com.example.delivaryUser.common.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun Modifier.autoMirror(): Modifier {
    val layoutDirection = LocalLayoutDirection.current
    return when (layoutDirection) {
        LayoutDirection.Rtl -> this
        LayoutDirection.Ltr -> this.scale(scaleX = -1f, scaleY = 1f)
    }
}