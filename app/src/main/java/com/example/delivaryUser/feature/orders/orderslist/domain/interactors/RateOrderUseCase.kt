package com.example.delivaryUser.feature.orders.orderslist.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class RateOrderUseCase(val repository: IOrdersRepository) : BaseUseCase<Flow<Resource<Unit>>, RateOrderRequest>() {
    override suspend fun invoke(body: RateOrderRequest): Flow<Resource<Unit>> =
        flowExecute { repository.rateOrder(body) }
}