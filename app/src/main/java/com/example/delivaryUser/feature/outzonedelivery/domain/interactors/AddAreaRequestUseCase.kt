package com.example.delivaryUser.feature.outzonedelivery.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.IOutZoneDeliveryRepository
import kotlinx.coroutines.flow.Flow

class AddAreaRequestUseCase(
    private val repository: IOutZoneDeliveryRepository
) : BaseUseCase<Flow<Resource<AddAreaResponse>>, AddAreaRequest>() {

    override suspend fun invoke(body: AddAreaRequest): Flow<Resource<AddAreaResponse>> =
        flowExecute {
            repository.addAreaRequest(request = body)
        }
}