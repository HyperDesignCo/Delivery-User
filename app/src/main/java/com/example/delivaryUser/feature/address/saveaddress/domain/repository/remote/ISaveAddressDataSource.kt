package com.example.delivaryUser.feature.address.saveaddress.domain.repository.remote

import com.example.delivaryUser.feature.address.saveaddress.data.model.dto.SaveAddressDto
import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest

interface ISaveAddressDataSource {
    suspend fun addAddress(request: AddAddressRequest,token: String): SaveAddressDto
}