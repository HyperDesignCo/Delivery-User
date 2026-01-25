package com.example.delivaryUser.feature.orders.orderslist.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(val repository: IOrdersRepository) : BaseUseCase<Flow<Resource<List<Order>>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<List<Order>>> = flowExecute {
        repository.getOrders()
    }
}