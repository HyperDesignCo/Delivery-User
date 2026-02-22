package com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserChatRequest(
    @SerialName("order_id")
    val orderId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("chat_id")
    val chatId: Int
)