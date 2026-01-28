package com.example.delivaryUser.feature.address.saveaddress.data.repository

import com.example.delivaryUser.feature.address.saveaddress.data.mapper.SaveAddressMapper
import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest
import com.example.delivaryUser.feature.address.saveaddress.domain.model.SaveAddress
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.ISaveAddressRepository
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.remote.ISaveAddressDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class SaveAddressRepository(
    private val remote: ISaveAddressDataSource,
    val local: IUserLocalDataSource
) : ISaveAddressRepository {


    override suspend fun addAddress(
        request: AddAddressRequest
    ): SaveAddress {
        val token = local.getToken()
        val user = local.getUser()
        return SaveAddressMapper.dtoToDomain(
            remote.addAddress(
                request = request.copy(name = user.name),
                token = token
            )
        )
    }

}