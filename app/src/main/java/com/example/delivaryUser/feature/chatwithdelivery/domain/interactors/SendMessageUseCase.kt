package com.example.delivaryUser.feature.chatwithdelivery.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import kotlinx.coroutines.flow.Flow

class SendMessageUseCase(
    private val repository: IChatWithDeliveryRepository
) : BaseUseCase<Flow<Resource<UserChat>>, UserAddMessageRequest>() {
    override suspend fun invoke(body: UserAddMessageRequest): Flow<Resource<UserChat>> =
        flowExecute {
            val errors = body.validateFields()
            if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
            repository.sendMessage(body)
        }
}