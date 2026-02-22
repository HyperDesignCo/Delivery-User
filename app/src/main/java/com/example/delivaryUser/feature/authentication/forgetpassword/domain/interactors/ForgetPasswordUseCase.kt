package com.example.delivaryUser.feature.authentication.forgetpassword.domain.interactors

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.model.domain.ForgetPassword
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository.IForgetPasswordRepository
import kotlinx.coroutines.flow.Flow

class ForgetPasswordUseCase(private val repository: IForgetPasswordRepository) :
    BaseUseCase<Flow<Resource<ForgetPassword>>, ForgetPasswordRequest>() {
    override suspend fun invoke(body: ForgetPasswordRequest): Flow<Resource<ForgetPassword>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.forgetPassword(body)
    }
}