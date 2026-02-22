package com.hyperdesign.delivaryUser.feature.chatwithai.domain.repository

import com.hyperdesign.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest

interface IChatWithAiRepository {
    suspend fun chatWithAi(request: ChatWithAiRequest): String
}