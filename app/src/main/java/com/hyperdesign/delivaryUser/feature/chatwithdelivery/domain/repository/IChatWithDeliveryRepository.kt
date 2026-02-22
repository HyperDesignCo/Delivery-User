package com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository

import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.model.UserChat

interface IChatWithDeliveryRepository {

    suspend fun getUserChat(request: UserChatRequest): UserChat

    suspend fun sendMessage(request: UserAddMessageRequest): UserChat
}