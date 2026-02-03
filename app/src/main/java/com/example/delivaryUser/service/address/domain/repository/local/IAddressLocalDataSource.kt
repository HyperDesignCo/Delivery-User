package com.example.delivaryUser.service.address.domain.repository.local

import com.example.delivaryUser.service.address.data.models.entity.AddressEntity

interface IAddressLocalDataSource {
    suspend fun saveSenderAddress(address: AddressEntity)
    suspend fun getSenderAddress(): AddressEntity
    suspend fun saveRecipientAddress(address: AddressEntity)
    suspend fun getRecipientAddress(): AddressEntity
    suspend fun deleteSenderAddress()
    suspend fun deleteRecipientAddress()
}