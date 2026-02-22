package com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository

import com.hyperdesign.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest

interface ICancelOrderRepository {
    suspend fun cancelOrder(request: CancelOrderRequest)
}