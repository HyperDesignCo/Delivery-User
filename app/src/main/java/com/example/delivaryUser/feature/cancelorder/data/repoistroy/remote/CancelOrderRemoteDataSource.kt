package com.example.delivaryUser.feature.cancelorder.data.repoistroy.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.example.delivaryUser.feature.cancelorder.domain.repository.remote.ICancelOrderRemoteDataSource
import com.example.delivaryUser.feature.orders.base.data.models.dto.NewOrderResponseDto

class CancelOrderRemoteDataSource(private val provider: IRemoteDataSourceProvider) : ICancelOrderRemoteDataSource {
    override suspend fun cancelOrder(request: CancelOrderRequest): NewOrderResponseDto = provider.postWithFile(
        endpoint = CANCEL_ORDER_ENDPOINT,
        requestBody = request.remoteRequest,
        files = request.remoteRequestWithFiles,
        serializer = NewOrderResponseDto.serializer()
    )

    companion object {
        const val CANCEL_ORDER_ENDPOINT = "usercancelorder"
    }
}