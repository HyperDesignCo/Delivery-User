package com.example.delivaryUser.feature.chatwithai.domain.repository.remote

import com.example.delivaryUser.feature.chatwithai.data.models.dto.ChatWithAiDto
import com.example.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest

interface IChatWithAiRemoteDataSource {
    suspend fun chatWithAi(request: ChatWithAiRequest) : ChatWithAiDto
}