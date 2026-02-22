package com.hyperdesign.delivaryUser.feature.pointtopoint.domain.usecase

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import kotlinx.coroutines.flow.Flow

class GetOrderPurposesUseCase(private val repository: IPointToPointRepository) :
    BaseUseCase<Flow<Resource<List<OrderPurpose>>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<List<OrderPurpose>>> = flowExecute {
        repository.getOrderPurposes()
    }
}