package com.example.delivaryUser.feature.outzonedelivery.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.outzonedelivery.data.model.dto.AddAreaResponseDto
import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.remote.IOutZoneDeliveryDataSource

class OutZoneDeliveryDataSource(
    val provider: IRemoteDataSourceProvider
) : IOutZoneDeliveryDataSource {

    override suspend fun addAreaRequest(request: AddAreaRequest): AddAreaResponseDto =
        provider.post(
            endpoint = USER_ADD_AREA_REQUEST_END_POINT,
            serializer = AddAreaResponseDto.serializer(),
            requestBody = request
        )

    companion object {
        const val USER_ADD_AREA_REQUEST_END_POINT = "UserAddAreaRequest"
    }
}