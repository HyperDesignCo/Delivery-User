package com.example.delivaryUser.feature.orders.base.data.repository

import com.example.delivaryUser.feature.orders.base.data.mappers.OrderMapper
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import com.example.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class OrdersRepository(val remote: IOrdersRemoteDataSource, val local: IUserLocalDataSource) : IOrdersRepository {
    override suspend fun getOrders(): List<Order> {
        val token = local.getToken()
        return remote.getOrders(token).orders.map { OrderMapper.dtoToDomain(it) }
    }

    override suspend fun getOrderById(id: Int): Order {
        val token = local.getToken()
        return OrderMapper.dtoToDomain(remote.getOrderById(id = id, token = token).order)
    }
}