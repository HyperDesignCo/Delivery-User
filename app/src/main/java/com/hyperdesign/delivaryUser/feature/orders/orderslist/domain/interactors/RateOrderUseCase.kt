package com.hyperdesign.delivaryUser.feature.orders.orderslist.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest
import com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class RateOrderUseCase(val repository: IOrdersRepository) : BaseUseCase<Flow<Resource<Unit>>, RateOrderRequest>() {
    override suspend fun invoke(body: RateOrderRequest): Flow<Resource<Unit>> =
        flowExecute { repository.rateOrder(body) }
}