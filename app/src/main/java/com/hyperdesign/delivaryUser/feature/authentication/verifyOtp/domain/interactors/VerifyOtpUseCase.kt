package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.interactors

import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import kotlinx.coroutines.flow.Flow

class VerifyOtpUseCase(private val repository: IVerifyOtpRepository) :
    BaseUseCase<Flow<Resource<Otp>>, VerifyOtpRequest>() {
    override suspend fun invoke(body: VerifyOtpRequest): Flow<Resource<Otp>> = flowExecute {
        val errors = body.validateFields()
        if (errors.isNotEmpty()) throw DelivaryUserException.Local.RequestValidation(errors = errors)
        repository.verifyOtp(body)
    }
}