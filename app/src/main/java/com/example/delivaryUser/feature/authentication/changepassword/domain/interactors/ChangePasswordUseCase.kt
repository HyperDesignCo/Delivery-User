package com.example.delivaryUser.feature.authentication.changepassword.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest
import com.example.delivaryUser.feature.authentication.changepassword.domain.repository.IChangePasswordRepository
import kotlinx.coroutines.flow.Flow

class ChangePasswordUseCase(private val repository: IChangePasswordRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, ChangePasswordRequest>() {
    override suspend fun invoke(body: ChangePasswordRequest): Flow<Resource<Authentication>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.changePassword(body)
    }
}