package com.hyperdesign.delivaryUser.feature.fastorder.domain.repository

import com.hyperdesign.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest

interface IFastOrderRepository {
    suspend fun createFastOrder(request: FastOrderRequest)
}