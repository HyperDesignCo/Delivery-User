package com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.repository.IChangePasswordRepository
import kotlinx.coroutines.flow.Flow

class ChangePasswordUseCase(private val repository: IChangePasswordRepository) :
    BaseUseCase<Flow<Resource<Authentication>>, ChangePasswordRequest>() {
    override suspend fun invoke(body: ChangePasswordRequest): Flow<Resource<Authentication>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.changePassword(body)
    }
}