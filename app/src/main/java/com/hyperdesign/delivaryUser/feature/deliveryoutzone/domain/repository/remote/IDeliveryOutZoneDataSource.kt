package com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.dto.AddAreaResponseDto
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest

interface IDeliveryOutZoneDataSource {
    suspend fun addArea(request: AddAreaRequest): AddAreaResponseDto
}