package com.example.delivaryUser.service.address.data.repository

import com.example.delivaryUser.service.address.data.mappers.AddressMapper
import com.example.delivaryUser.service.address.data.models.dto.AddressDto
import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import com.example.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
import com.example.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class AddressRepository(
    private val remote: IAddressRemoteDataSource,
    private val local: IUserLocalDataSource,
    private val addressLocalDataSource: IAddressLocalDataSource,
) : IAddressRepository {
    override suspend fun getAddresses(): List<Address> {
        val token = local.getToken()
        return remote.getAddresses(token).address?.map { AddressMapper.dtoToDomain(it) } ?: emptyList()
    }

    override suspend fun saveSenderAddress(address: Address) =
        addressLocalDataSource.saveSenderAddress(AddressMapper.domainToEntity(address))


    override suspend fun getSenderAddress(): Address =
        AddressMapper.entityToDomain(addressLocalDataSource.getSenderAddress())


    override suspend fun saveRecipientAddress(address: Address) =
        addressLocalDataSource.saveRecipientAddress(AddressMapper.domainToEntity(address))


    override suspend fun getRecipientAddress(): Address =
        AddressMapper.entityToDomain(addressLocalDataSource.getRecipientAddress())

    override suspend fun saveAddress(request: AddAddressRequest): Address {
        val token = local.getToken()
        val user = local.getUser()
        return AddressMapper.dtoToDomain(
            remote.saveAddress(
                request = request.copy(name = user.name),
                token = token
            ).address ?: AddressDto()
        )
    }
}