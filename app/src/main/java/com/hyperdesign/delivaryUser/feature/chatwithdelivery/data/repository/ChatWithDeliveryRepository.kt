package com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.repository

import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.mapper.UserChatMapper
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.remote.IChatWithDeliveryDataSource

class ChatWithDeliveryRepository(private val remote: IChatWithDeliveryDataSource) :
    IChatWithDeliveryRepository {

    override suspend fun getUserChat(request: UserChatRequest): UserChat =
        UserChatMapper.dtoToDomain(remote.getUserChat(request = request))

    override suspend fun sendMessage(request: UserAddMessageRequest): UserChat =
        UserChatMapper.dtoToDomain(remote.sendMessage(request))
}