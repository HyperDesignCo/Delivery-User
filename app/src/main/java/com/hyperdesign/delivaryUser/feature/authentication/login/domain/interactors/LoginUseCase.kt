package com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: IAuthenticationRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, LoginRequest>() {
    override suspend fun invoke(body: LoginRequest): Flow<Resource<Authentication>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.login(body)
    }
}