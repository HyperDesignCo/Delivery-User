package com.example.delivaryUser.feature.fastorder.domain.repository.remote

import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

interface IFastOrderRemoteDataSource {
    suspend fun createFastOrder(request: FastOrderRequest, token: String): NewOrderResponseDto
}