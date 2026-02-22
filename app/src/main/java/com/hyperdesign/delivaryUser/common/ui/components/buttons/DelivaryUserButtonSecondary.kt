package com.hyperdesign.delivaryUser.common.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonDefaults.color
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserButtonSecondary(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: DelivaryUserButtonColors = DelivaryUserButtonSecondaryDefaults.colors,
    isEnabled: Boolean = true,
) {
    DelivaryUserButton(
        label = label,
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        isEnabled = isEnabled,
        labelStyle = DelivaryUserButtonSecondaryDefaults.labelStyle
    )
}

object DelivaryUserButtonSecondaryDefaults {
    val labelStyle
        @Composable get()
        = DelivaryUserTheme.typography.body.extraSmall

    val colors: DelivaryUserButtonColors
        @Composable get() =
            color(
                containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                contentColor = DelivaryUserTheme.colors.primary,
                disabledContainerColor = DelivaryUserTheme.colors.background.disable,
                disabledContentColor = DelivaryUserTheme.colors.background.surfaceHigh,
                boarderColor = DelivaryUserTheme.colors.background.stoke
            )
}

@Composable
@PreviewAllVariants
@Preview(showBackground = true)
private fun DelivaryUserButtonSecondaryPreview() = DelivaryUserTheme {
    DelivaryUserButtonSecondary(
        label = "login",
        onClick = {},
        isEnabled = false
    )
}