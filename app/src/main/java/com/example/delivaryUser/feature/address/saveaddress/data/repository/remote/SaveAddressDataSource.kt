package com.example.delivaryUser.feature.address.saveaddress.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.address.saveaddress.data.model.dto.SaveAddressDto
import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.remote.ISaveAddressDataSource

class SaveAddressDataSource(
    private val provider: IRemoteDataSourceProvider
): ISaveAddressDataSource {

    override suspend fun addAddress(request: AddAddressRequest, token: String): SaveAddressDto =
        provider.post(
            endpoint = USER_ADD_ADDRESS_ENDPOINT,
            requestBody = request,
            headers = mapOf("Authorization" to "Bearer $token"),
            serializer = SaveAddressDto.serializer()
        )


    companion object{
        const val USER_ADD_ADDRESS_ENDPOINT = "useraddAddress"
    }
}