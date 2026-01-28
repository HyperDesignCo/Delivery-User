package com.example.delivaryUser.feature.pointtopoint.domain.repository

import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.example.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest

interface IPointToPointRepository {
    suspend fun getOrderPurposes(): List<OrderPurpose>
    suspend fun getDeliveryCost(request: DeliveryCostRequest): DeliveryCost
    suspend fun addNewOrder(request: PointToPointRequest): Order
}