package com.example.delivaryUser.feature.chatwithai.domain.repository

import com.example.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest

interface IChatWithAiRepository {
    suspend fun chatWithAi(request: ChatWithAiRequest): String
}