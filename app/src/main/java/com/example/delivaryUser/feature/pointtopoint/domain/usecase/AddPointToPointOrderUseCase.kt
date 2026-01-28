package com.example.delivaryUser.feature.pointtopoint.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest
import com.example.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import kotlinx.coroutines.flow.Flow

class AddPointToPointOrderUseCase(private val repository: IPointToPointRepository) :
    BaseUseCase<Flow<Resource<Order>>, PointToPointRequest>() {
    override suspend fun invoke(body: PointToPointRequest): Flow<Resource<Order>> = flowExecute {
        repository.addNewOrder(request = body)
    }
}