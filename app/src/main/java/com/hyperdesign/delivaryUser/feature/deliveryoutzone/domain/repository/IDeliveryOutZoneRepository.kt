package com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository

import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest

interface IDeliveryOutZoneRepository {
    suspend fun addArea(request: AddAreaRequest)
}