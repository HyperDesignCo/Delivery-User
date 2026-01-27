package com.example.delivaryUser.service.address.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow

class SaveSenderAddressUseCase(private val repository: IAddressRepository) :
    BaseUseCase<Flow<Resource<Unit>>, Address>() {
    override suspend fun invoke(body: Address): Flow<Resource<Unit>> = flowExecute {
        repository.saveSenderAddress(body)
    }
}