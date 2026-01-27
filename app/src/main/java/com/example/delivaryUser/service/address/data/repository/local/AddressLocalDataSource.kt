package com.example.delivaryUser.service.address.data.repository.local

import com.example.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.example.delivaryUser.service.address.data.models.entity.AddressEntity
import com.example.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
import kotlinx.serialization.json.Json

class AddressLocalDataSource(private val provider: ILocalDataSourceProvider, private val json: Json) :
    IAddressLocalDataSource {
    override suspend fun saveSenderAddress(address: AddressEntity) {
        provider.save(
            LocalDataSourceEnum.SENDER_ADDRESS,
            value = json.encodeToString(address),
            type = String::class.java
        )
    }

    override suspend fun getSenderAddress(): AddressEntity = json.decodeFromString(
        string = provider.read(
            key = LocalDataSourceEnum.SENDER_ADDRESS, defaultValue = "", type = String::class.java
        ), deserializer = AddressEntity.serializer()
    )


    override suspend fun saveRecipientAddress(address: AddressEntity) {
        provider.save(
            LocalDataSourceEnum.RECIPIENT_ADDRESS,
            value = json.encodeToString(address),
            type = String::class.java
        )
    }

    override suspend fun getRecipientAddress(): AddressEntity = json.decodeFromString(
        string = provider.read(
            key = LocalDataSourceEnum.RECIPIENT_ADDRESS, defaultValue = "", type = String::class.java
        ), deserializer = AddressEntity.serializer()
    )
}