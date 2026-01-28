package com.example.delivaryUser.feature.address.saveaddress.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest
import com.example.delivaryUser.feature.address.saveaddress.domain.model.SaveAddress
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.ISaveAddressRepository
import kotlinx.coroutines.flow.Flow

class AddAddressUseCase(
    private val repository: ISaveAddressRepository
) : BaseUseCase<Flow<Resource<SaveAddress>>, AddAddressRequest>() {
    override suspend fun invoke(body: AddAddressRequest): Flow<Resource<SaveAddress>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
            repository.addAddress(body)
        }

}