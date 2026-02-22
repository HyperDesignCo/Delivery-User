package com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote

import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.dto.OtpDto
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request.ResendOtpRequest

interface IVerifyOtpRemoteDataSource {
    suspend fun verifyOtp(request: VerifyOtpRequest): OtpDto
    suspend fun resendCode(request: ResendOtpRequest): OtpDto
}