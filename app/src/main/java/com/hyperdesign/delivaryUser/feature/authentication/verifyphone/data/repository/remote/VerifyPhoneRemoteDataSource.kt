package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.dto.VerifyPhoneDto
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.remote.IVerifyPhoneRemoteDataSource

class VerifyPhoneRemoteDataSource(private val provider: IRemoteDataSourceProvider) :
    IVerifyPhoneRemoteDataSource {
    override suspend fun verifyPhone(request: VerifyPhoneRequest): VerifyPhoneDto =
        provider.post(
            endpoint = VERIFY_PHONE_ENDPOINT,
            requestBody = request,
            serializer = VerifyPhoneDto.serializer()
        )

    companion object {
        const val VERIFY_PHONE_ENDPOINT = "verify_phone"
    }
}