package com.example.delivaryUser.feature.fastorder.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource
import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

class FastOrderRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IFastOrderRemoteDataSource {
    override suspend fun createFastOrder(request: FastOrderRequest, token: String): NewOrderResponseDto =
        provider.postWithFile(
            endpoint = FAST_ORDER_END_POINT,
            headers = mapOf("Authorization" to "Bearer $token"),
            files = request.remoteRequestWithFiles,
            requestBody = request.remoteRequest,
            serializer = NewOrderResponseDto.serializer()
        )
    companion object {
        const val FAST_ORDER_END_POINT = "UserAddOrder"
    }
}