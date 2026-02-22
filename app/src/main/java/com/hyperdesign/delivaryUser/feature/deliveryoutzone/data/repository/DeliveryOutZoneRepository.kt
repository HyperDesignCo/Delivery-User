package com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.repository

import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.IDeliveryOutZoneRepository
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.remote.IDeliveryOutZoneDataSource

class DeliveryOutZoneRepository(
    private val remote: IDeliveryOutZoneDataSource
) : IDeliveryOutZoneRepository {
    override suspend fun addArea(request: AddAreaRequest) {
        remote.addArea(request)
    }
}