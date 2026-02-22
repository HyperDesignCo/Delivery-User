package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository

import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp

interface IVerifyOtpRepository {
    suspend fun verifyOtp(request: VerifyOtpRequest): Otp
    suspend fun resendCode(): Otp
}