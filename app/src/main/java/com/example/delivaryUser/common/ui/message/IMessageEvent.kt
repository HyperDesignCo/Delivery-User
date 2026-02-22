package com.example.delivaryUser.common.ui.message

import androidx.annotation.StringRes
import com.example.delivaryUser.common.ui.extension.UIText

sealed interface IMessageEvent {
    val message: UIText
    val messageType: MessageType

    data class Toast(
        override val message: UIText,
        override val messageType: MessageType = MessageType.DEFAULT,
    ) : IMessageEvent

    data class Snackbar(
        override val message: UIText,
        val withDismissAction: Boolean = false,
        private val defaultDuration: MessageDuration = MessageDuration.SHORT,
        val action: MessageAction? = null,
        override val messageType: MessageType,
    ) : IMessageEvent {
        val isRetry: Boolean =
            action != null || messageType == MessageType.RETRY
        val duration =
            MessageDuration.SHORT
    }

}

data class MessageAction(
    @param:StringRes val labelResId: Int,
    val action: () -> Unit,
)

enum class MessageDuration {
    SHORT,
    LONG,
    INDEFINITE
}