package com.hyperdesign.delivaryUser.feature.pointtopoint.domain.usecase

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import kotlinx.coroutines.flow.Flow

class GetDeliveryCostUseCase(private val repository: IPointToPointRepository) :
    BaseUseCase<Flow<Resource<DeliveryCost>>, DeliveryCostRequest>() {
    override suspend fun invoke(body: DeliveryCostRequest): Flow<Resource<DeliveryCost>> = flowExecute {
        repository.getDeliveryCost(body)
    }
}