package com.example.delivaryUser.service.address.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.service.address.data.models.dto.AddressResponseDto
import com.example.delivaryUser.service.address.data.models.dto.AddressesResponseDto
import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.example.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource

class AddressRemoteDataSource(val provider: IRemoteDataSourceProvider) : IAddressRemoteDataSource {
    override suspend fun getAddresses(): AddressesResponseDto = provider.get(
        endpoint = ADDRESSES_ENDPOINT,
        serializer = AddressesResponseDto.serializer(),
    )

    override suspend fun saveAddress(
        request: AddAddressRequest,
    ): AddressResponseDto = provider.post(
        endpoint = USER_ADD_ADDRESS_ENDPOINT,
        requestBody = request,
        serializer = AddressResponseDto.serializer()
    )

    companion object {
        const val ADDRESSES_ENDPOINT = "usergetaddress"
        const val USER_ADD_ADDRESS_ENDPOINT = "useraddAddress"
    }
}