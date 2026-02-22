package com.hyperdesign.delivaryUser.common.ui.components.bars.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.extension.autoMirror
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelivaryUserTopBar(
    onStartIconClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
    @DrawableRes startIcon: Int? = R.drawable.ic_back,
    content: (@Composable () -> Unit)? = null,
    @DrawableRes endIcon: Int? = null,
    onEndIconClicked: () -> Unit = {},
    colors: DeliveryUserTopBarColors = DeliveryUserTopBarDefaults.colors(),
    rowContent: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(DeliveryUserTopBarDefaults.height)
            .background(color = colors.containerColor)
            .padding(
                start = DeliveryUserTopBarDefaults.startPadding,
                end = DeliveryUserTopBarDefaults.endPadding
            ),
        verticalAlignment = DeliveryUserTopBarDefaults.verticalAlignment,
        horizontalArrangement = DeliveryUserTopBarDefaults.horizontalArrangement
    ) {
        IconButton(
            modifier = Modifier.size(DeliveryUserTopBarDefaults.iconSize),
            onClick = { onStartIconClicked() }) {
            startIcon?.let {
                Icon(
                    modifier = Modifier.autoMirror(),
                    imageVector = ImageVector.vectorResource(startIcon),
                    tint = colors.contentColor,
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
        ) {
            if (rowContent != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    content = rowContent
                )
            } else {
                content?.invoke() ?: Image(
                    modifier = Modifier.size(
                        width = DeliveryUserTopBarDefaults.logoWidth,
                        height = DeliveryUserTopBarDefaults.logoHeight
                    ),
                    painter = painterResource(R.drawable.img_topbar_logo),
                    contentDescription = null
                )
            }
        }

        endIcon?.let {
            IconButton(
                modifier = Modifier.size(DeliveryUserTopBarDefaults.iconSize),
                onClick = { onEndIconClicked() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(endIcon),
                    tint = colors.contentColor,
                    contentDescription = null
                )
            }
        } ?: Spacer(modifier = Modifier)
    }
}

data class DeliveryUserTopBarColors(
    val containerColor: Color,
    val contentColor: Color,
)

object DeliveryUserTopBarDefaults {
    val height = 50.dp
    val startPadding = 16.dp
    val endPadding = 10.dp
    val verticalAlignment = Alignment.CenterVertically
    val horizontalArrangement = Arrangement.SpaceBetween
    val iconSize = 24.dp
    val logoWidth = 122.dp
    val logoHeight = 50.dp

    @Composable
    fun colors(
        containerColor: Color = DelivaryUserTheme.colors.primary,
        contentColor: Color = DelivaryUserTheme.colors.background.surfaceHigh,
    ): DeliveryUserTopBarColors =
        DeliveryUserTopBarColors(containerColor = containerColor, contentColor = contentColor)
}

@Composable
@PreviewAllVariants
private fun DelivaryUserTopBarPreview() = DelivaryUserTheme {
    DelivaryUserTopBar(
        onStartIconClicked = {},
    )
}