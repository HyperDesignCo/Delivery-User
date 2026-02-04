package com.example.delivaryUser.common.ui.components.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DeliveryUserSnackbar(
    message: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    colors: DeliveryUserSnackbarColors = DeliveryUserSnackbarDefaults.colors(),
    shape: RoundedCornerShape = DeliveryUserSnackbarDefaults.shape,
    horizontalArrangement: Dp = DeliveryUserSnackbarDefaults.horizontalArrangement,
    boarderWidth: Dp = DeliveryUserSnackbarDefaults.borderWidth,
    iconSize: Dp = DeliveryUserSnackbarDefaults.iconSize,
) {
    Snackbar(
        modifier = modifier
            .fillMaxWidth()
            .border(width = boarderWidth, color = colors.borderColor, shape = shape),
        shape = shape,
        containerColor = colors.containerColor,
    ) {
        Row(
            Modifier
                .background(color = colors.containerColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(horizontalArrangement)
        ) {
            icon?.let {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = icon,
                    tint = colors.iconColor,
                    contentDescription = null
                )
            }
            Text(
                modifier= Modifier.fillMaxWidth(),
                text = message,
                style = DelivaryUserTheme.typography.body.medium,
                color = DelivaryUserTheme.colors.secondary
            )
        }
    }
}

data class DeliveryUserSnackbarColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val iconColor: Color,
)

object DeliveryUserSnackbarDefaults {
    val shape = RoundedCornerShape(16.dp)
    val horizontalArrangement = 8.dp
    val borderWidth = 1.dp
    val iconSize = 24.dp

    @Composable
    fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        borderColor: Color = Color.Unspecified,
        iconColor: Color = Color.Unspecified,
    ) = DeliveryUserSnackbarColors(
        containerColor = containerColor,
        contentColor = contentColor,
        borderColor = borderColor,
        iconColor = iconColor
    )

}

@PreviewLightDark
@Composable
private fun DeliveryUserSnackbarPreview() = DelivaryUserTheme {
    DeliveryUserSnackbar(
        message = "Some error happened.",
        icon = ImageVector.vectorResource(R.drawable.ic_snack_bar_fail),
        colors = DeliveryUserSnackbarColors(
            containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            contentColor = DelivaryUserTheme.colors.secondary,
            borderColor = DelivaryUserTheme.colors.secondary,
            iconColor = DelivaryUserTheme.colors.status.redAccent
        ),
    )
}