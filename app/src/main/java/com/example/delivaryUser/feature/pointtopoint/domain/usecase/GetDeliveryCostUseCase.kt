package com.example.delivaryUser.feature.pointtopoint.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.example.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import kotlinx.coroutines.flow.Flow

class GetDeliveryCostUseCase(private val repository: IPointToPointRepository) :
    BaseUseCase<Flow<Resource<DeliveryCost>>, DeliveryCostRequest>() {
    override suspend fun invoke(body: DeliveryCostRequest): Flow<Resource<DeliveryCost>> = flowExecute {
        repository.getDeliveryCost(body)
    }
}