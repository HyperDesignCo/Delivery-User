package com.example.delivaryUser.feature.cancelorder.domain.repository

import com.example.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest

interface ICancelOrderRepository {
    suspend fun cancelOrder(request: CancelOrderRequest)
}