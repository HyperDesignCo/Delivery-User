package com.hyperdesign.delivaryUser.common.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun Modifier.autoMirror(): Modifier {
    val layoutDirection = LocalLayoutDirection.current
    return when (layoutDirection) {
        LayoutDirection.Ltr -> this
        LayoutDirection.Rtl -> this.scale(scaleX = -1f, scaleY = 1f)
    }
}
@Composable
fun Modifier.clickableWithNoRipple(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.clickable(
        onClick = onClick,
        interactionSource = interactionSource,
        indication = null,
        enabled = enabled
    )
}