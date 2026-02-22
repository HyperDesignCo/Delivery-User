package com.hyperdesign.delivaryUser.feature.orders.orderslist.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(val repository: IOrdersRepository) : BaseUseCase<Flow<Resource<List<Order>>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<List<Order>>> = flowExecute {
        repository.getOrders()
    }
}