package com.example.delivaryUser.feature.chatwithdelivery.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import kotlinx.coroutines.flow.Flow

class GetUserChatUseCase(
    private val repository: IChatWithDeliveryRepository
) : BaseUseCase<Flow<Resource<UserChat>>, UserChatRequest>() {

    override suspend fun invoke(body: UserChatRequest): Flow<Resource<UserChat>> = flowExecute {
        repository.getUserChat(body)
    }

}
