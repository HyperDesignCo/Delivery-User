package com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.dto.UserChatDto
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.remote.IChatWithDeliveryDataSource

class ChatWithDeliveryDataSource(private val provider: IRemoteDataSourceProvider) :
    IChatWithDeliveryDataSource {

    override suspend fun getUserChat(request: UserChatRequest): UserChatDto =
        provider.post(
            endpoint = USER_CHAT_ENDPOINT,
            requestBody = request,
            serializer = UserChatDto.serializer()
        )

    override suspend fun sendMessage(request: UserAddMessageRequest): UserChatDto =
        provider.post(
            endpoint = USER_ADD_MESSAGES_ENDPOINT,
            requestBody = request,
            serializer = UserChatDto.serializer()
        )
    companion object {
        const val USER_ADD_MESSAGES_ENDPOINT = "useraddMessages"
        const val USER_CHAT_ENDPOINT = "userchat"
    }
}