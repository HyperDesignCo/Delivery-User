package com.hyperdesign.delivaryUser.feature.fastorder.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.hyperdesign.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

class FastOrderRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IFastOrderRemoteDataSource {
    override suspend fun createFastOrder(request: FastOrderRequest): NewOrderResponseDto =
        provider.postWithFile(
            endpoint = FAST_ORDER_END_POINT,
            files = request.remoteRequestWithFiles,
            requestBody = request.remoteRequest,
            serializer = NewOrderResponseDto.serializer()
        )

    companion object {
        const val FAST_ORDER_END_POINT = "UserAddOrder"
    }
}