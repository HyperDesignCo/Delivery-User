package com.hyperdesign.delivaryUser.feature.address.saveaddress.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.hyperdesign.delivaryUser.service.address.domain.models.domain.Address
import com.hyperdesign.delivaryUser.service.address.domain.repository.IAddressRepository
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