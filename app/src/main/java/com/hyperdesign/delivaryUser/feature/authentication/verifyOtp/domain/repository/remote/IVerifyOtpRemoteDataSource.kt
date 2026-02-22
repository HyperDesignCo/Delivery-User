package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.dto.OtpDto
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.ResendOtpRequest

interface IVerifyOtpRemoteDataSource {
    suspend fun verifyOtp(request: VerifyOtpRequest): OtpDto
    suspend fun resendCode(request: ResendOtpRequest): OtpDto
}