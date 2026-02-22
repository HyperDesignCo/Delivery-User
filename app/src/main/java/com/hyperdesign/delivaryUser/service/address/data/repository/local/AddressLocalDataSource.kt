package com.hyperdesign.delivaryUser.service.address.data.repository.local

import com.hyperdesign.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.hyperdesign.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.hyperdesign.delivaryUser.service.address.data.models.entity.AddressEntity
import com.hyperdesign.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
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

    override suspend fun getSenderAddress(): AddressEntity {
        val jsonString = provider.read(
            key = LocalDataSourceEnum.SENDER_ADDRESS,
            defaultValue = "",
            type = String::class.java
        )
        return if (jsonString.isNotEmpty()) {
            json.decodeFromString(
                deserializer = AddressEntity.serializer(),
                string = jsonString
            )
        } else {
            AddressEntity()
        }
    }


    override suspend fun saveRecipientAddress(address: AddressEntity) {
        provider.save(
            LocalDataSourceEnum.RECIPIENT_ADDRESS,
            value = json.encodeToString(address),
            type = String::class.java
        )
    }

    override suspend fun getRecipientAddress(): AddressEntity {
        val jsonString = provider.read(
            key = LocalDataSourceEnum.RECIPIENT_ADDRESS,
            defaultValue = "",
            type = String::class.java
        )

        return if (jsonString.isNotEmpty()) {
            json.decodeFromString(
                deserializer = AddressEntity.serializer(),
                string = jsonString
            )
        } else {
            AddressEntity()
        }
    }

    override suspend fun deleteSenderAddress() = provider.delete<String>(
        key = LocalDataSourceEnum.SENDER_ADDRESS,
        type = String::class.java
    )

    override suspend fun deleteRecipientAddress() = provider.delete<String>(
        key = LocalDataSourceEnum.RECIPIENT_ADDRESS,
        type = String::class.java
    )
}