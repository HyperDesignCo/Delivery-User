package com.example.delivaryUser.feature.orders.base.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrderResponseDto
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrdersDto
import com.example.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource

class OrdersRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IOrdersRemoteDataSource {
    override suspend fun getOrders(token: String): OrdersDto = provider.get(
        headers = mapOf("Authorization" to "Bearer $token"),
        endpoint = ORDERS_ENDPOINT,
        serializer = OrdersDto.serializer(),
    )

    override suspend fun getOrderById(
        token: String,
        id: Int,
    ): OrderResponseDto = provider.get(
        headers = mapOf("Authorization" to "Bearer $token"),
        params = mapOf("id" to id),
        endpoint = ORDER_DETAILS,
        serializer = OrderResponseDto.serializer(),
    )

    companion object {
        const val ORDERS_ENDPOINT = "usergetorders"
        const val ORDER_DETAILS = "userorderdetails"
    }
}