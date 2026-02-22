package com.hyperdesign.delivaryUser.feature.cancelorder.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.ICancelOrderRepository
import kotlinx.coroutines.flow.Flow

class CancelOrderUseCase(private val repository: ICancelOrderRepository) :
    BaseUseCase<Flow<Resource<Unit>>, CancelOrderRequest>() {
    override suspend fun invoke(body: CancelOrderRequest): Flow<Resource<Unit>> = flowExecute {
        repository.cancelOrder(body)
    }
}