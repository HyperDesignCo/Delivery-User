package com.example.delivaryUser.feature.orders.base.data.repository

import com.example.delivaryUser.feature.orders.base.data.mappers.OrderMapper
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import com.example.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource

class OrdersRepository(val remote: IOrdersRemoteDataSource) : IOrdersRepository {
    override suspend fun getOrders(): List<Order> {
        return remote.getOrders().orders.map { OrderMapper.dtoToDomain(it) }
    }

    override suspend fun getOrderById(id: Int): Order {
        return OrderMapper.dtoToDomain(remote.getOrderById(id = id).order)
    }
}