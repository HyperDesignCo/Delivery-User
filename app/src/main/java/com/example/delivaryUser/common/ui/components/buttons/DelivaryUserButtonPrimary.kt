package com.example.delivaryUser.common.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserButtonPrimary(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    DelivaryUserButton(
        label = label,
        onClick = onClick,
        modifier = modifier,
        colors = DelivaryUserButtonPrimaryDefaults.colors,
        isEnabled = isEnabled,
    )
}

object DelivaryUserButtonPrimaryDefaults {
    val colors: DelivaryUserButtonColors
        @Composable get() =
            DelivaryUserButtonDefaults.color(
                containerColor = DelivaryUserTheme.colors.primary,
                contentColor = DelivaryUserTheme.colors.background.surfaceHigh,
                disabledContainerColor = DelivaryUserTheme.colors.background.disable,
                disabledContentColor = DelivaryUserTheme.colors.background.surfaceHigh,
                boarderColor = DelivaryUserTheme.colors.primary
            )
}

@Composable
@PreviewAllVariants
@Preview(showBackground = true)
private fun DelivaryUserButtonPrimaryPreview() = DelivaryUserTheme {
    DelivaryUserButtonPrimary(
        label = "login",
        onClick = {},
        isEnabled = false
    )
}