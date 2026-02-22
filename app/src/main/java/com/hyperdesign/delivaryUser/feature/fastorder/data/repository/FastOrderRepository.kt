package com.hyperdesign.delivaryUser.feature.fastorder.data.repository

import com.hyperdesign.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.hyperdesign.delivaryUser.feature.fastorder.domain.repository.IFastOrderRepository
import com.hyperdesign.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource

class FastOrderRepository(private val remote: IFastOrderRemoteDataSource) :
    IFastOrderRepository {
    override suspend fun createFastOrder(request: FastOrderRequest) {
        remote.createFastOrder(request)
    }
}