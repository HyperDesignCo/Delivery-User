package com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository

import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest

interface IPointToPointRepository {
    suspend fun getOrderPurposes(): List<OrderPurpose>
    suspend fun getDeliveryCost(request: DeliveryCostRequest): DeliveryCost
    suspend fun addNewOrder(request: PointToPointRequest): Order
}