package com.example.delivaryUser.feature.authentication.verifyOtp.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import kotlinx.coroutines.flow.Flow

class ResendCodeUseCase(private val repository: IVerifyOtpRepository) :
    BaseUseCase<Flow<Resource<Otp>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Otp>> = flowExecute {
        repository.resendCode()
    }
}