package com.example.delivaryUser.feature.orders.base.domain.repository.remote

import com.example.delivaryUser.feature.orders.base.data.models.dto.OrderResponseDto
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrdersDto

interface IOrdersRemoteDataSource {
    suspend fun getOrders(token: String): OrdersDto
    suspend fun getOrderById(token: String, id: Int): OrderResponseDto
}