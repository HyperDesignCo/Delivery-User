package com.example.delivaryUser.feature.userdata.editaccount.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.IEditAccountRepository
import kotlinx.coroutines.flow.Flow

class EditAccountUseCase(private val repository: IEditAccountRepository) :
    BaseUseCase<Flow<Resource<Unit>>, EditAccountRequest>() {
    override suspend fun invoke(body: EditAccountRequest): Flow<Resource<Unit>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.editAccount(body)
    }
}