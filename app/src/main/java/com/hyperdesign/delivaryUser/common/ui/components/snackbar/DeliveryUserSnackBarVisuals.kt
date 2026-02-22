package com.hyperdesign.delivaryUser.common.ui.components.snackbar

import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable

data class DeliveryUserSnackbarVisuals(
    val messageType: MessageType,
    override val actionLabel: String,
    override val duration: SnackbarDuration,
    override val message: String,
    override val withDismissAction: Boolean,
) : SnackbarVisuals {
    val colors: DeliveryUserSnackbarColors
        @Composable
        get() = when (messageType) {
            MessageType.SUCCESS -> DeliveryUserSnackbarColors(
                containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                contentColor = DelivaryUserTheme.colors.secondary,
                borderColor = DelivaryUserTheme.colors.background.stoke,
                iconColor = DelivaryUserTheme.colors.status.greenAccent
            )

            MessageType.ERROR -> DeliveryUserSnackbarColors(
                containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                contentColor = DelivaryUserTheme.colors.secondary,
                borderColor = DelivaryUserTheme.colors.background.stoke,
                iconColor = DelivaryUserTheme.colors.status.redAccent
            )


            MessageType.RETRY -> DeliveryUserSnackbarColors(
                containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                contentColor = DelivaryUserTheme.colors.secondary,
                borderColor =DelivaryUserTheme.colors.background.stoke,
                iconColor = DelivaryUserTheme.colors.status.redAccent
            )

            MessageType.DEFAULT ->  DeliveryUserSnackbarColors(
                containerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                contentColor = DelivaryUserTheme.colors.secondary,
                borderColor = DelivaryUserTheme.colors.background.stoke,
                iconColor = DelivaryUserTheme.colors.status.greenAccent
            )
        }
    val iconRes : Int
        @DrawableRes get() = when(messageType) {
            MessageType.SUCCESS -> R.drawable.ic_snack_bar_success
            MessageType.ERROR -> R.drawable.ic_snack_bar_fail
            MessageType.RETRY ->  R.drawable.ic_snack_bar_fail
            MessageType.DEFAULT -> R.drawable.ic_snack_bar_success
        }
}