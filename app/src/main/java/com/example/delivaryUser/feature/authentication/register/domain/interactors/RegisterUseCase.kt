package com.example.delivaryUser.feature.authentication.register.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.example.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val repository: IAuthenticationRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, RegisterRequest>() {
    override suspend fun invoke(body: RegisterRequest): Flow<Resource<Authentication>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.register(body)
    }
}