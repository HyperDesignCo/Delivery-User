package com.example.delivaryUser.feature.outzonedelivery.domain.repository.remote

import com.example.delivaryUser.feature.outzonedelivery.data.model.dto.AddAreaResponseDto
import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest

interface IOutZoneDeliveryDataSource {
    suspend fun addAreaRequest(request: AddAreaRequest): AddAreaResponseDto
}