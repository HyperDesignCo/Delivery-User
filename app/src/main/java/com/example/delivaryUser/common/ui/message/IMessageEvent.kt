package com.example.delivaryUser.common.ui.message

import com.example.delivaryUser.common.ui.extension.UIText

sealed interface IMessageEvent {
    val message: UIText
    val messageType: MessageType

    data class Toast(
        override val message: UIText,
        override val messageType: MessageType = MessageType.DEFAULT
    ) : IMessageEvent
}