package com.example.delivaryUser.feature.cancelorder.domain.repository.remote

import com.example.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

interface ICancelOrderRemoteDataSource {
    suspend fun cancelOrder(request: CancelOrderRequest): NewOrderResponseDto
}