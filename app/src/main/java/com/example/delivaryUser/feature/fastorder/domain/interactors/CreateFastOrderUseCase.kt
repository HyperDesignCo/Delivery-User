package com.example.delivaryUser.feature.fastorder.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.fastorder.domain.repository.IFastOrderRepository
import kotlinx.coroutines.flow.Flow

class CreateFastOrderUseCase(private val repository: IFastOrderRepository) :
    BaseUseCase<Flow<Resource<Unit>>, FastOrderRequest>() {
    override suspend fun invoke(body: FastOrderRequest) = flowExecute {
        repository.createFastOrder(body)
    }
}