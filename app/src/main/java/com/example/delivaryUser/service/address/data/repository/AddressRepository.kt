package com.example.delivaryUser.service.address.data.repository

import com.example.delivaryUser.service.address.data.mappers.AddressMapper
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import com.example.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
import com.example.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class AddressRepository(
    private val remote: IAddressRemoteDataSource,
    private val localUser: IUserLocalDataSource,
    private val addressLocal: IAddressLocalDataSource,
) : IAddressRepository {
    override suspend fun getAddresses(): List<Address> {
        val token = localUser.getToken()
        return remote.getAddresses(token).address?.map { AddressMapper.dtoToDomain(it) } ?: emptyList()
    }

    override suspend fun saveSenderAddress(address: Address) =
        addressLocal.saveSenderAddress(AddressMapper.domainToEntity(address))


    override suspend fun getSenderAddress(): Address =
        AddressMapper.entityToDomain(addressLocal.getSenderAddress())


    override suspend fun saveRecipientAddress(address: Address) =
        addressLocal.saveRecipientAddress(AddressMapper.domainToEntity(address))


    override suspend fun getRecipientAddress(): Address =
        AddressMapper.entityToDomain(addressLocal.getRecipientAddress())

    override suspend fun deleteSenderAddress() = addressLocal.deleteSenderAddress()

    override suspend fun deleteRecipientAddress() = addressLocal.deleteRecipientAddress()
}