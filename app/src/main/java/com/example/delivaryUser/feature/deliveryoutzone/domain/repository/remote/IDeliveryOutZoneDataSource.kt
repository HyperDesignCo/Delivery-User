package com.example.delivaryUser.feature.deliveryoutzone.domain.repository.remote

import com.example.delivaryUser.feature.deliveryoutzone.data.model.dto.AddAreaResponseDto
import com.example.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest

interface IDeliveryOutZoneDataSource {
    suspend fun addArea(request: AddAreaRequest): AddAreaResponseDto
}