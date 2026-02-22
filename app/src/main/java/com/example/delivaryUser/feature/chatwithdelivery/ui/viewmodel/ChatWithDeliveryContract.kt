package com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel

import com.example.delivaryUser.common.ui.filedstate.TextFieldState
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.ChatMessage
import com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryContract.ChatWithDeliveryUiState

sealed interface ChatWithDeliveryContract {
    sealed interface Action {
        data class MessageChanged(val message: String) : Action
        data object NavigateBacKClicked : Action
        data object SendMessageClicked : Action
        data object OnCallDriverClicked : Action
    }

    data class State(
        val message: TextFieldState = TextFieldState(),
        val deliveryImage: String = "",
        val deliveryName: String = "",
        val deliveryNumber:String = "",
        val chatId: Int = 0,
        val chatMessages: List<ChatWithDeliveryUiState> = emptyList()
    )

    data class ChatWithDeliveryUiState(
        val id: Int = 0,
        val providerName: String = "",
        val userName: String = "",
        val message: String = "",
        val senderType: String = "",
        val sender: String = "",
        val createAt: String = ""

    )
}

fun ChatMessage.toUiState() = ChatWithDeliveryUiState(
    id = this.id,
    providerName = this.providerName,
    userName = this.userName,
    message = this.message,
    senderType = this.senderType,
    sender = this.sender,
    createAt = this.createAt
)