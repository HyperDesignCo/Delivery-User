package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.dto.OtpDto
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.request.ResendOtpRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote.IVerifyOtpRemoteDataSource

class VerifyOtpRemoteDataSource(val remoteProvider: IRemoteDataSourceProvider) : IVerifyOtpRemoteDataSource {
    override suspend fun verifyOtp(request: VerifyOtpRequest): OtpDto = remoteProvider.post(
        endpoint = VERIFY_OTP_ENDPOINT,
        requestBody = request,
        serializer = OtpDto.serializer()
    )

    override suspend fun resendCode(request: ResendOtpRequest): OtpDto = remoteProvider.post(
        endpoint = RESEND_CODE,
        requestBody = request,
        serializer = OtpDto.serializer()
    )

    companion object {
        const val VERIFY_OTP_ENDPOINT = "otp"
        const val RESEND_CODE = "resend_code"
    }
}