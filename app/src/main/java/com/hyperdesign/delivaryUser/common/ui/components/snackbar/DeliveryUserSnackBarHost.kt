package com.hyperdesign.delivaryUser.common.ui.components.snackbar

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageDuration

@Composable
fun DeliveryUserSnackBarHost(SnackbarHostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(modifier = modifier, hostState = SnackbarHostState) { SnackbarHostState ->
        val snackbarVisuals = SnackbarHostState.visuals as DeliveryUserSnackbarVisuals

        DeliveryUserSnackbar(
            modifier = Modifier.padding(16.dp),
            message = snackbarVisuals.message,
            colors = snackbarVisuals.colors,
            icon = ImageVector.vectorResource(snackbarVisuals.iconRes)
        )
    }
}


suspend fun SnackbarHostState.showSnackbar(context: Context, event: IMessageEvent.Snackbar) {
    currentSnackbarData?.dismiss()

    showSnackbar(
        DeliveryUserSnackbarVisuals(
            message = event.message.asString(context),
            messageType = event.messageType,
            duration = event.duration.toSnackbarDuration(),
            withDismissAction = event.withDismissAction,
            actionLabel = event.action?.labelResId?.let(context::getString).orEmpty()
        )
    )
}

private fun MessageDuration.toSnackbarDuration(): SnackbarDuration = when (this) {
    MessageDuration.SHORT -> SnackbarDuration.Short
    MessageDuration.LONG -> SnackbarDuration.Long
    MessageDuration.INDEFINITE -> SnackbarDuration.Indefinite
}