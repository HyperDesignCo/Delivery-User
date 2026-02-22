package com.hyperdesign.delivaryUser.feature.cancelorder.data.repoistroy

import com.hyperdesign.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.ICancelOrderRepository
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.remote.ICancelOrderRemoteDataSource

class CancelOrderRepository(private val remote: ICancelOrderRemoteDataSource) : ICancelOrderRepository {
    override suspend fun cancelOrder(request: CancelOrderRequest) {
        remote.cancelOrder(request = request)
    }
}