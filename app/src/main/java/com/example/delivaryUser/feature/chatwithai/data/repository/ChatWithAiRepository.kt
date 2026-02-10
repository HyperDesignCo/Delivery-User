package com.example.delivaryUser.feature.chatwithai.data.repository

import com.example.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest
import com.example.delivaryUser.feature.chatwithai.domain.repository.IChatWithAiRepository
import com.example.delivaryUser.feature.chatwithai.domain.repository.remote.IChatWithAiRemoteDataSource

class ChatWithAiRepository(private val remote: IChatWithAiRemoteDataSource) :
    IChatWithAiRepository {
    override suspend fun chatWithAi(request: ChatWithAiRequest): String = remote.chatWithAi(request).html.orEmpty()
}