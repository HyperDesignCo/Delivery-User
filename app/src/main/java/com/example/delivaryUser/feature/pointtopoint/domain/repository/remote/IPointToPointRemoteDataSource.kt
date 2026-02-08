package com.example.delivaryUser.feature.pointtopoint.domain.repository.remote

import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.DeliveryCostResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.OrderPurposesResponseDto
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest

interface IPointToPointRemoteDataSource {
    suspend fun getOrderPurposes(token: String): OrderPurposesResponseDto
    suspend fun getDeliveryCost(token: String, request: DeliveryCostRequest): DeliveryCostResponseDto
    suspend fun addNewOrder(token: String, request: PointToPointRequest): NewOrderResponseDto
}