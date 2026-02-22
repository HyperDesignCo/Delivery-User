package com.hyperdesign.delivaryUser.service.location.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.repository.remote.ILocationRemoteDataSource

class LocationRemoteDataSource(
    private val provider: IRemoteDataSourceProvider,
) : ILocationRemoteDataSource {
    override suspend fun checkLocation(
        request: CheckLocationRequest,
    ): CheckLocationDto =
        provider.post(
            endpoint = CHECK_LOCATION_ENDPOINT,
            serializer = CheckLocationDto.serializer(),
            requestBody = request
        )

    companion object {
        const val CHECK_LOCATION_ENDPOINT = "checkLocation"
    }
}