package com.example.delivaryUser.feature.address.saveaddress.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow

class SaveAddressUseCase(
    private val repository: IAddressRepository,
) : BaseUseCase<Flow<Resource<Address>>, AddAddressRequest>() {
    override suspend fun invoke(body: AddAddressRequest): Flow<Resource<Address>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.saveAddress(body)
    }
}