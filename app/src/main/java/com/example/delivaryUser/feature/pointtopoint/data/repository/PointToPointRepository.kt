package com.example.delivaryUser.feature.pointtopoint.data.repository

import com.example.delivaryUser.feature.orders.base.data.mappers.OrderMapper
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.pointtopoint.data.models.mappers.DeliveryCostMapper
import com.example.delivaryUser.feature.pointtopoint.data.models.mappers.OrderPurposeMapper
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest
import com.example.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.example.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.example.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import com.example.delivaryUser.feature.pointtopoint.domain.repository.remote.IPointToPointRemoteDataSource

class PointToPointRepository(private val remote: IPointToPointRemoteDataSource) :
    IPointToPointRepository {
    override suspend fun getOrderPurposes(): List<OrderPurpose> {
        return OrderPurposeMapper.dtoToDomain(remote.getOrderPurposes().orderPurposes)
    }

    override suspend fun getDeliveryCost(request: DeliveryCostRequest): DeliveryCost {
        return DeliveryCostMapper.dtoToDomain(remote.getDeliveryCost(request = request))
    }

    override suspend fun addNewOrder(request: PointToPointRequest): Order {
        return OrderMapper.dtoToDomain(remote.addNewOrder(request = request).order)
    }
}