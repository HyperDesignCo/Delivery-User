package com.example.delivaryUser.feature.address.saveaddress.domain.repository

import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest
import com.example.delivaryUser.feature.address.saveaddress.domain.model.SaveAddress

interface ISaveAddressRepository {
    suspend fun addAddress(request: AddAddressRequest): SaveAddress
}