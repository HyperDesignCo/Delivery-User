package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository

import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest

interface IVerifyPhoneRepository {
    suspend fun verifyPhone(request: VerifyPhoneRequest): String
}