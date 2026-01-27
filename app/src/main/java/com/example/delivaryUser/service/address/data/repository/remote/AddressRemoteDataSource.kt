package com.example.delivaryUser.service.address.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.service.address.data.models.dto.AddressesResponseDto
import com.example.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource

class AddressRemoteDataSource(val provider: IRemoteDataSourceProvider) : IAddressRemoteDataSource {
    override suspend fun getAddresses(token: String): AddressesResponseDto = provider.get(
        headers = mapOf("Authorization" to "Bearer $token"),
        endpoint = ADDRESSES_ENDPOINT,
        serializer = AddressesResponseDto.serializer(),
    )

    companion object {
        const val ADDRESSES_ENDPOINT = "usergetaddress"
    }
}