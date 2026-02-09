package com.example.delivaryUser.feature.fastorder.data.repository

import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.fastorder.domain.repository.IFastOrderRepository
import com.example.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource

class FastOrderRepository(private val remote: IFastOrderRemoteDataSource) :
    IFastOrderRepository {
    override suspend fun createFastOrder(request: FastOrderRequest) {
        remote.createFastOrder(request)
    }
}