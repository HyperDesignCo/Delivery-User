package com.example.delivaryUser.feature.orders.base.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrderResponseDto
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrdersDto
import com.example.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource

class OrdersRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IOrdersRemoteDataSource {
    override suspend fun getOrders(): OrdersDto = provider.get(
        endpoint = ORDERS_ENDPOINT,
        serializer = OrdersDto.serializer(),
    )

    override suspend fun getOrderById(
        id: Int,
    ): OrderResponseDto = provider.get(
        params = mapOf("id" to id),
        endpoint = ORDER_DETAILS,
        serializer = OrderResponseDto.serializer(),
    )
    companion object {
        const val ORDERS_ENDPOINT = "usergetorders"
        const val ORDER_DETAILS = "userorderdetails"
    }
}