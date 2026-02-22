package com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.dto.UserChatDto
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest

interface IChatWithDeliveryDataSource {

    suspend fun getUserChat(request: UserChatRequest): UserChatDto

    suspend fun sendMessage(request: UserAddMessageRequest): UserChatDto
}