package com.example.delivaryUser.feature.outzonedelivery.data.repository

import com.example.delivaryUser.feature.outzonedelivery.data.mapper.AddAreaResponseMapper
import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.IOutZoneDeliveryRepository
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.remote.IOutZoneDeliveryDataSource

class OutZoneDeliveryRepository(
    private val remote: IOutZoneDeliveryDataSource
) : IOutZoneDeliveryRepository {

    override suspend fun addAreaRequest(request: AddAreaRequest): AddAreaResponse =
        AddAreaResponseMapper.dtoToDomain(remote.addAreaRequest(request))
}