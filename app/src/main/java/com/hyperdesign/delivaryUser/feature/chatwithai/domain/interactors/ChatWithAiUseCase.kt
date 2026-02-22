package com.hyperdesign.delivaryUser.feature.chatwithai.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest
import com.hyperdesign.delivaryUser.feature.chatwithai.domain.repository.IChatWithAiRepository
import kotlinx.coroutines.flow.Flow

class ChatWithAiUseCase(private val repository: IChatWithAiRepository) :
    BaseUseCase<Flow<Resource<String>>, ChatWithAiRequest>() {
    override suspend fun invoke(body: ChatWithAiRequest): Flow<Resource<String>> = flowExecute {
        repository.chatWithAi(request = body)
    }
}