package com.example.delivaryUser.feature.orders.base.domain.repository

import com.example.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order

interface IOrdersRepository {
    suspend fun getOrders(): List<Order>
    suspend fun getOrderById(id: Int): Order
    suspend fun rateOrder(request: RateOrderRequest)
}