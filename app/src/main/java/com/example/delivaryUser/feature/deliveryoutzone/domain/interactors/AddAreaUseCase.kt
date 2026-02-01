package com.example.delivaryUser.feature.deliveryoutzone.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.deliveryoutzone.domain.repository.IDeliveryOutZoneRepository
import kotlinx.coroutines.flow.Flow

class AddAreaUseCase(
    private val repository: IDeliveryOutZoneRepository,
) : BaseUseCase<Flow<Resource<Unit>>, AddAreaRequest>() {
    override suspend fun invoke(body: AddAreaRequest): Flow<Resource<Unit>> =
        flowExecute { repository.addArea(request = body) }
}