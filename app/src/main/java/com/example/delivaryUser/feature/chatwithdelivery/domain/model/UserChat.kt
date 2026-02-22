package com.example.delivaryUser.feature.chatwithdelivery.domain.model

data class UserChat(
    val message: String,
    val chatMessages: List<ChatMessage>,
    val chatId: Int
)


data class ChatMessage(
    val id: Int,
    val providerName: String,
    val deliveryName: String,
    val userName: String,
    val message: String,
    val senderType: String,
    val sender: String,
    val createAt: String,
)