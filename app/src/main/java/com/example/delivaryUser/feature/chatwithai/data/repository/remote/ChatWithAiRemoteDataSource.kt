package com.example.delivaryUser.feature.chatwithai.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.chatwithai.data.models.dto.ChatWithAiDto
import com.example.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest
import com.example.delivaryUser.feature.chatwithai.domain.repository.remote.IChatWithAiRemoteDataSource

class ChatWithAiRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IChatWithAiRemoteDataSource {
    override suspend fun chatWithAi(
        request: ChatWithAiRequest,
    ): ChatWithAiDto = provider.get(
        endpoint = CHAT_WITH_AI_ENDPOINT,
        params = mapOf(
            "lat" to request.latitude,
            "lng" to request.longitude,
        ),
        serializer = ChatWithAiDto.serializer(),
    )

    companion object {
        const val CHAT_WITH_AI_ENDPOINT = "chatWithAI"
    }
}