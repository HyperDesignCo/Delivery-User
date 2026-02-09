package com.example.delivaryUser.feature.orders.base.domain.repository.remote

import com.example.delivaryUser.feature.orders.base.data.models.dto.OrderResponseDto
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrdersDto

interface IOrdersRemoteDataSource {
    suspend fun getOrders(): OrdersDto
    suspend fun getOrderById(id: Int): OrderResponseDto
}