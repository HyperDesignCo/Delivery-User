package com.hyperdesign.delivaryUser.feature.fastorder.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

interface IFastOrderRemoteDataSource {
    suspend fun createFastOrder(request: FastOrderRequest): NewOrderResponseDto
}