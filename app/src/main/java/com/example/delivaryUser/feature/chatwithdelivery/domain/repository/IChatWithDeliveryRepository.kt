package com.example.delivaryUser.feature.chatwithdelivery.domain.repository

import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat

interface IChatWithDeliveryRepository {

    suspend fun getUserChat(request: UserChatRequest): UserChat

    suspend fun sendMessage(request: UserAddMessageRequest): UserChat
}