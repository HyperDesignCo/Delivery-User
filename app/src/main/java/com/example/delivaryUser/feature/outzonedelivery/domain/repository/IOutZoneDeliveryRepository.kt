package com.example.delivaryUser.feature.outzonedelivery.domain.repository

import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse

interface IOutZoneDeliveryRepository {
    suspend fun addAreaRequest(request: AddAreaRequest): AddAreaResponse
}