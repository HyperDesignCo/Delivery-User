package com.example.delivaryUser.service.address.domain.repository.remote

import com.example.delivaryUser.service.address.data.models.dto.AddressesResponseDto

interface IAddressRemoteDataSource {
    suspend fun getAddresses(token: String): AddressesResponseDto
}