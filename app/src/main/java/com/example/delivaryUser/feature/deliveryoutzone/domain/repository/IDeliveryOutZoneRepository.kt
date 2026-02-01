package com.example.delivaryUser.feature.deliveryoutzone.domain.repository

import com.example.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest

interface IDeliveryOutZoneRepository {
    suspend fun addArea(request: AddAreaRequest)
}