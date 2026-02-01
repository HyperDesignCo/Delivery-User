package com.example.delivaryUser.service.address.domain.repository.remote

import com.example.delivaryUser.service.address.data.models.dto.AddressResponseDto
import com.example.delivaryUser.service.address.data.models.dto.AddressesResponseDto
import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest

interface IAddressRemoteDataSource {
    suspend fun getAddresses(token: String): AddressesResponseDto
    suspend fun saveAddress(request: AddAddressRequest, token: String): AddressResponseDto
}