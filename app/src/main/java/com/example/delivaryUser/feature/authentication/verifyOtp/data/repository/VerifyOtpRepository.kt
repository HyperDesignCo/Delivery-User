package com.example.delivaryUser.feature.authentication.verifyOtp.data.repository

import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.mapper.VerifyOtpMapper
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request.ResendOtpRequest
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote.IVerifyOtpRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class VerifyOtpRepository(val remote: IVerifyOtpRemoteDataSource, val local: IUserLocalDataSource) :
    IVerifyOtpRepository {
    override suspend fun verifyOtp(request: VerifyOtpRequest): Otp {
        val token = local.getToken()
        val user = local.getUser()
        val result = remote.verifyOtp(request.copy(phone = user.phone), token)
        local.saveIsVerified(true)
        return VerifyOtpMapper.dtoToDomain(model = result)
    }

    override suspend fun resendCode(): Otp {
        val token = local.getToken()
        val user = local.getUser()
        val request = ResendOtpRequest(user.phone)
        val result = remote.resendCode(request, token)
        local.saveIsVerified(true)
        return VerifyOtpMapper.dtoToDomain(model = result)
    }
}