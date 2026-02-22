package com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import kotlinx.coroutines.flow.Flow

class GetUserChatUseCase(
    private val repository: IChatWithDeliveryRepository
) : BaseUseCase<Flow<Resource<UserChat>>, UserChatRequest>() {

    override suspend fun invoke(body: UserChatRequest): Flow<Resource<UserChat>> = flowExecute {
        repository.getUserChat(body)
    }

}
