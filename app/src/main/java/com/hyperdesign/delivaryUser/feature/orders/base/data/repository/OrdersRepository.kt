package com.hyperdesign.delivaryUser.feature.orders.base.data.repository

import com.hyperdesign.delivaryUser.feature.orders.base.data.mappers.OrderMapper
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource

class OrdersRepository(val remote: IOrdersRemoteDataSource) : IOrdersRepository {
    override suspend fun getOrders(): List<Order> {
        return remote.getOrders().orders.map { OrderMapper.dtoToDomain(it) }
    }

    override suspend fun getOrderById(id: Int): Order {
        return OrderMapper.dtoToDomain(remote.getOrderById(id = id).order)
    }

    override suspend fun rateOrder(request: RateOrderRequest) {
        remote.rateOrder(request)
    }
}