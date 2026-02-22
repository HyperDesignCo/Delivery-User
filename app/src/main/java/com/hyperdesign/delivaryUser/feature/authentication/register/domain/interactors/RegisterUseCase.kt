package com.hyperdesign.delivaryUser.feature.authentication.register.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val repository: IAuthenticationRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, RegisterRequest>() {
    override suspend fun invoke(body: RegisterRequest): Flow<Resource<Authentication>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.register(body)
    }
}