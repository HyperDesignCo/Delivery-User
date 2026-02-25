package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.dto.VerifyPhoneDto
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest

interface IVerifyPhoneRemoteDataSource {
    suspend fun verifyPhone(request: VerifyPhoneRequest): VerifyPhoneDto
}