package com.hyperdesign.delivaryUser.feature.pointtopoint.data.repository

import com.hyperdesign.delivaryUser.feature.orders.base.data.mappers.OrderMapper
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.mappers.DeliveryCostMapper
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.mappers.OrderPurposeMapper
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.remote.IPointToPointRemoteDataSource

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