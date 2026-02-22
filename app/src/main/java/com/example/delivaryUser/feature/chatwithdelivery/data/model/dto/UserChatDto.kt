package com.example.delivaryUser.feature.chatwithdelivery.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserChatDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("chat_messages")
    val chatMessages: List<ChatMessageDto>? = null,
    @SerialName("chat_id")
    val chatId: Int? = null,
)

@Serializable
data class ChatMessageDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("provider_name")
    val providerName: String? = null,
    @SerialName("delivery_name")
    val deliveryName: String? = null,
    @SerialName("user_name")
    val userName: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("sender_type")
    val senderType: String? = null,
    @SerialName("sender")
    val sender: String? = null,
    @SerialName("created_at")
    val createAt: String? = null,
)