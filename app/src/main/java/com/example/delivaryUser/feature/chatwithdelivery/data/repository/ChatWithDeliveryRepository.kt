package com.example.delivaryUser.feature.chatwithdelivery.data.repository

import com.example.delivaryUser.feature.chatwithdelivery.data.mapper.UserChatMapper
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.remote.IChatWithDeliveryDataSource

class ChatWithDeliveryRepository(private val remote: IChatWithDeliveryDataSource) :
    IChatWithDeliveryRepository {

    override suspend fun getUserChat(request: UserChatRequest): UserChat =
        UserChatMapper.dtoToDomain(remote.getUserChat(request = request))

    override suspend fun sendMessage(request: UserAddMessageRequest): UserChat =
        UserChatMapper.dtoToDomain(remote.sendMessage(request))
}