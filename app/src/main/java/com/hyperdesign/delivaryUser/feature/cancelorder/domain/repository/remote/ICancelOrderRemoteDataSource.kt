package com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

interface ICancelOrderRemoteDataSource {
    suspend fun cancelOrder(request: CancelOrderRequest): NewOrderResponseDto
}