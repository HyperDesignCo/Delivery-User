package com.example.delivaryUser.service.address.domain.repository

import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.example.delivaryUser.service.address.domain.models.domain.Address

interface IAddressRepository {
    suspend fun getAddresses(): List<Address>
    suspend fun saveSenderAddress(address: Address)
    suspend fun getSenderAddress(): Address
    suspend fun saveRecipientAddress(address: Address)
    suspend fun getRecipientAddress(): Address
    suspend fun saveAddress(request: AddAddressRequest): Address
}