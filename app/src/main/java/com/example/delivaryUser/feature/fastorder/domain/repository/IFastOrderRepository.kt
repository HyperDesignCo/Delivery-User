package com.example.delivaryUser.feature.fastorder.domain.repository

import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest

interface IFastOrderRepository {
    suspend fun createFastOrder(request: FastOrderRequest)
}