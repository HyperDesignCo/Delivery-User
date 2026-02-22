package com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.dto.AddAreaResponseDto
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.remote.IDeliveryOutZoneDataSource

class DeliveryOutZoneDataSource(
    private val provider: IRemoteDataSourceProvider
) : IDeliveryOutZoneDataSource {
    override suspend fun addArea(request: AddAreaRequest): AddAreaResponseDto =
        provider.post(
            endpoint = USER_ADD_AREA_REQUEST_END_POINT,
            serializer = AddAreaResponseDto.serializer(),
            requestBody = request
        )

    companion object {
        const val USER_ADD_AREA_REQUEST_END_POINT = "UserAddAreaRequest"
    }
}