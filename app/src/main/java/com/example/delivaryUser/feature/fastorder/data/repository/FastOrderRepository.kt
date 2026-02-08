package com.example.delivaryUser.feature.fastorder.data.repository

import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.fastorder.domain.repository.IFastOrderRepository
import com.example.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class FastOrderRepository(private val remote: IFastOrderRemoteDataSource, val local: IUserLocalDataSource) :
    IFastOrderRepository {
    override suspend fun createFastOrder(request: FastOrderRequest) {
        val token = local.getToken()
        remote.createFastOrder(request, token)
    }
}