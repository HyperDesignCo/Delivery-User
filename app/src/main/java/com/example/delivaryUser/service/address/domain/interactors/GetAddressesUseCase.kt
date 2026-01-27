package com.example.delivaryUser.service.address.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow

class GetAddressesUseCase(private val repository: IAddressRepository) :
    BaseUseCase<Flow<Resource<List<Address>>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<List<Address>>> = flowExecute {
        repository.getAddresses()
    }
}