package com.hyperdesign.delivaryUser.feature.orders.orderdetails.domain.ineractors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrderDetailsUseCase(val repository: IOrdersRepository): BaseUseCase<Flow<Resource<Order>>, Int>() {
    override suspend fun invoke(body: Int): Flow<Resource<Order>> = flowExecute {
        repository.getOrderById(body)
    }
}