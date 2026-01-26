package com.example.delivaryUser.feature.orders.orderdetails.domain.ineractors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrderDetailsUseCase(val repository: IOrdersRepository): BaseUseCase<Flow<Resource<Order>>, Int>() {
    override suspend fun invoke(body: Int): Flow<Resource<Order>> = flowExecute {
        repository.getOrderById(body)
    }
}