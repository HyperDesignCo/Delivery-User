package com.example.delivaryUser.feature.pointtopoint.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.DeliveryCostResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.OrderPurposesResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest
import com.example.delivaryUser.feature.pointtopoint.domain.repository.remote.IPointToPointRemoteDataSource

class PointToPointRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IPointToPointRemoteDataSource {
    override suspend fun getOrderPurposes(): OrderPurposesResponseDto = provider.get(
        endpoint = ORDER_PURPOSES_ENDPOINT,
        serializer = OrderPurposesResponseDto.serializer(),
    )

    override suspend fun getDeliveryCost(
        request: DeliveryCostRequest,
    ): DeliveryCostResponseDto = provider.post(
        requestBody = request,
        endpoint = DELIVERY_COST_ENDPOINT,
        serializer = DeliveryCostResponseDto.serializer(),
    )

    override suspend fun addNewOrder(request: PointToPointRequest): NewOrderResponseDto = provider.post(
        endpoint = ADD_NEW_ORDER_ENDPOINT,
        requestBody = request,
        serializer = NewOrderResponseDto.serializer(),
    )

    companion object {
        const val ORDER_PURPOSES_ENDPOINT = "getOrderPurposes"
        const val DELIVERY_COST_ENDPOINT = "deliveryCost"
        const val ADD_NEW_ORDER_ENDPOINT = "UserAddOrder"
    }
}