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
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class PointToPointRepository(private val remote: IPointToPointRemoteDataSource, val local: IUserLocalDataSource) :
    IPointToPointRepository {
    override suspend fun getOrderPurposes(): List<OrderPurpose> {
        val token = local.getToken()
        return OrderPurposeMapper.dtoToDomain(remote.getOrderPurposes(token).orderPurposes)
    }

    override suspend fun getDeliveryCost(request: DeliveryCostRequest): DeliveryCost {
        val token = local.getToken()
        return DeliveryCostMapper.dtoToDomain(remote.getDeliveryCost(token = token, request = request))
    }

    override suspend fun addNewOrder(request: PointToPointRequest): Order {
        val token = local.getToken()
        return OrderMapper.dtoToDomain(remote.addNewOrder(token = token, request = request).order)
    }
}