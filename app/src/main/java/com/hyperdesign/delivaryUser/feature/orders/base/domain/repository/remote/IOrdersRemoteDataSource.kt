package com.hyperdesign.delivaryUser.feature.orders.base.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.OrderResponseDto
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.OrdersDto
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.RateOrderResponseDto
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest

interface IOrdersRemoteDataSource {
    suspend fun getOrders(): OrdersDto
    suspend fun getOrderById(id: Int): OrderResponseDto
    suspend fun rateOrder(request: RateOrderRequest): RateOrderResponseDto
}