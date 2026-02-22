package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import kotlinx.coroutines.flow.Flow

class ResendCodeUseCase(private val repository: IVerifyOtpRepository) :
    BaseUseCase<Flow<Resource<Otp>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Otp>> = flowExecute {
        repository.resendCode()
    }
}