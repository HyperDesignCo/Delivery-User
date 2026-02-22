package com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.dto.DeliveryCostResponseDto
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.dto.OrderPurposesResponseDto
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest

interface IPointToPointRemoteDataSource {
    suspend fun getOrderPurposes(): OrderPurposesResponseDto
    suspend fun getDeliveryCost(request: DeliveryCostRequest): DeliveryCostResponseDto
    suspend fun addNewOrder(request: PointToPointRequest): NewOrderResponseDto
}