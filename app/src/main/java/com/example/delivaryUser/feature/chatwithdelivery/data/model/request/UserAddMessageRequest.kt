package com.example.delivaryUser.feature.chatwithdelivery.data.model.request

import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAddMessageRequest(
    @SerialName("chat_id")
    val chatId: Int,
    @SerialName("delivery_id")
    val deliveryId: Int,
    @SerialName("message")
    val message: String,
){
    private fun validateMessage() = message.isNotEmpty()

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validateMessage()) errors[ErrorKeyEnum.USER_MESSAGE] =
            RequestErrorKeyValues.USER_MESSAGE_VALIDATION
        return errors
    }
}
