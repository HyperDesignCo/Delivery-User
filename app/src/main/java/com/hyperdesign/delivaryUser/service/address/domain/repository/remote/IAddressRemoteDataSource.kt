package com.hyperdesign.delivaryUser.service.address.domain.repository.remote

import com.hyperdesign.delivaryUser.service.address.data.models.dto.AddressResponseDto
import com.hyperdesign.delivaryUser.service.address.data.models.dto.AddressesResponseDto
import com.hyperdesign.delivaryUser.service.address.data.models.request.AddAddressRequest

interface IAddressRemoteDataSource {
    suspend fun getAddresses(): AddressesResponseDto
    suspend fun saveAddress(request: AddAddressRequest): AddressResponseDto
}