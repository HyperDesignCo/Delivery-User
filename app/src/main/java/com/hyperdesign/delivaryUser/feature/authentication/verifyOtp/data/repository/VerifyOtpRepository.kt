package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.repository

import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.mapper.VerifyOtpMapper
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.ResendOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote.IVerifyOtpRemoteDataSource
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class VerifyOtpRepository(val remote: IVerifyOtpRemoteDataSource, val local: IUserLocalDataSource) :
    IVerifyOtpRepository {
    override suspend fun verifyOtp(request: VerifyOtpRequest): Otp {
        val result = remote.verifyOtp(request)
        local.saveIsVerified(true)
        return VerifyOtpMapper.dtoToDomain(model = result)
    }

    override suspend fun resendCode(): Otp {
        val user = local.getUser()
        val request = ResendOtpRequest(user.phone)
        val result = remote.resendCode(request)
        local.saveIsVerified(true)
        return VerifyOtpMapper.dtoToDomain(model = result)
    }
}