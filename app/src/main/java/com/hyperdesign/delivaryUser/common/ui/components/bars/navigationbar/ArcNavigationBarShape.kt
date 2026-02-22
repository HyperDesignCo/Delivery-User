package com.hyperdesign.delivaryUser.common.ui.components.bars.navigationbar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ArcNavigationBarShape(
    private val arcWidth: Dp = 100.dp,
    private val arcHeight: Dp = 30.dp,
    private val cornerRadius: Dp = 20.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val arcWidthPx = with(density) { arcWidth.toPx() }
        val arcHeightPx = with(density) { arcHeight.toPx() }
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }

        val path = Path().apply {
            val centerX = size.width / 2f
            val arcLeft = centerX - (arcWidthPx / 2f)
            val arcRight = centerX + (arcWidthPx / 2f)

            moveTo(0f, size.height)

            lineTo(0f, cornerRadiusPx)

            quadraticBezierTo(
                0f, 0f,
                cornerRadiusPx, 0f
            )

            lineTo(arcLeft, 0f)

            cubicTo(
                arcLeft, 0f,
                arcLeft, arcHeightPx * 0.8f,
                centerX - (arcWidthPx / 4f), arcHeightPx
            )

            cubicTo(
                centerX - (arcWidthPx / 8f), arcHeightPx * 1.1f,
                centerX + (arcWidthPx / 8f), arcHeightPx * 1.1f,
                centerX + (arcWidthPx / 4f), arcHeightPx
            )

            cubicTo(
                arcRight, arcHeightPx * 0.8f,
                arcRight, 0f,
                arcRight, 0f
            )

            lineTo(size.width - cornerRadiusPx, 0f)

            quadraticBezierTo(
                size.width, 0f,
                size.width, cornerRadiusPx
            )

            lineTo(size.width, size.height)

            lineTo(0f, size.height)

            close()
        }

        return Outline.Generic(path)
    }
}