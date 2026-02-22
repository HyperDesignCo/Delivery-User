package com.hyperdesign.delivaryUser.feature.chatwithai.data.repository

import com.hyperdesign.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest
import com.hyperdesign.delivaryUser.feature.chatwithai.domain.repository.IChatWithAiRepository
import com.hyperdesign.delivaryUser.feature.chatwithai.domain.repository.remote.IChatWithAiRemoteDataSource

class ChatWithAiRepository(private val remote: IChatWithAiRemoteDataSource) :
    IChatWithAiRepository {
    override suspend fun chatWithAi(request: ChatWithAiRequest): String = remote.chatWithAi(request).html.orEmpty()
}