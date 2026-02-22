package com.hyperdesign.delivaryUser.service.address.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.address.domain.models.domain.Address
import com.hyperdesign.delivaryUser.service.address.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow

class GetRecipientAddressUseCase(private val repository: IAddressRepository) :
    BaseUseCase<Flow<Resource<Address>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Address>> = flowExecute {
        repository.getRecipientAddress()
    }
}