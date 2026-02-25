package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.IVerifyPhoneRepository
import kotlinx.coroutines.flow.Flow

class VerifyPhoneUseCase(private val repository: IVerifyPhoneRepository) :
    BaseUseCase<Flow<Resource<String>>, VerifyPhoneRequest>() {
    override suspend fun invoke(body: VerifyPhoneRequest): Flow<Resource<String>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.verifyPhone(body)
    }
}